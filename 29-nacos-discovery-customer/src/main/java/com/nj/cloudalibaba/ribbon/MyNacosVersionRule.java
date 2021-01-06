package com.nj.cloudalibaba.ribbon;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 南江
 * @Description: 自定义实现版本号负载均衡
 * @date 2021/1/7 1:06
 */
public class MyNacosVersionRule extends AbstractLoadBalancerRule {

    private static final Logger log = LoggerFactory.getLogger(MyNacosVersionRule.class);

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        // 负载均衡规则：优先选择同集群下，符合metadata的实例
        //              没有同集群实例，就选择所有集群下，符合metadata的实例
        try {
            String clusterName = this.nacosDiscoveryProperties.getClusterName();
            String version = this.nacosDiscoveryProperties.getMetadata().get("version");

            DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer) getLoadBalancer();
            String name = loadBalancer.getName();

            NamingService namingService = this.nacosDiscoveryProperties.namingServiceInstance();
            //所有实例
            List<Instance> instances = namingService.selectInstances(name, true);
            List<Instance> metadataMatchInstances = instances;

            // 如果配置了版本映射，那么只调用元数据匹配的实例
            if (StringUtils.isNotBlank(version)){
                //JDK8流士编程
                metadataMatchInstances = instances.stream()
                        .filter(instance -> Objects.equals(version,instance.getMetadata().get("version")))
                        .collect(Collectors.toList());
                if (CollectionUtils.isEmpty(metadataMatchInstances)){
                    log.warn("未找到元数据匹配的目标服务实例, 请检查配置: targetVersion = {}, instance = {}", version, instances);
                    return null;
                }
            }

            List<Instance> clusterMetadataMatchInstances = metadataMatchInstances;

            // 如果配置了集群名称，需筛选同集群下元数据匹配的实例
            if (StringUtils.isNotBlank(clusterName)) {
                clusterMetadataMatchInstances = metadataMatchInstances.stream()
                        .filter(instance -> Objects.equals(clusterName, instance.getClusterName()))
                        .collect(Collectors.toList());
                if (CollectionUtils.isEmpty(clusterMetadataMatchInstances)) {
                    clusterMetadataMatchInstances = metadataMatchInstances;
                    log.warn("发生跨集群调用, clusterName = {}, targetVersion = {}, clusterMetadataMatchInstances = {}", clusterName, version, clusterMetadataMatchInstances);
                }
            }
            Instance instance = ExtendBalancer.getHostByRandomWeight2(clusterMetadataMatchInstances);
            return new NacosServer(instance);

        }catch (Exception e){
            log.error("发生异常", e);
            return null;
        }
    }
}

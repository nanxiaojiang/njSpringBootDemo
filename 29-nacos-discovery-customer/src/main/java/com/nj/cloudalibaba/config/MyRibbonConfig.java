package com.nj.cloudalibaba.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import com.nj.cloudalibaba.ribbon.MyNacosRule;
import com.nj.cloudalibaba.ribbon.MyNacosVersionRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/6 23:25
 */
@Configuration
public class MyRibbonConfig {

    /**
     * ribbon负载均衡，默认是：ZoneAvoidanceRule
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 也可以使用配置文件指定负载均衡策略
     * @return  CTRL+h 打开Hierarchy面板
     */
    @Bean
    public IRule iRule(){
        return new MyNacosVersionRule();
    }
}

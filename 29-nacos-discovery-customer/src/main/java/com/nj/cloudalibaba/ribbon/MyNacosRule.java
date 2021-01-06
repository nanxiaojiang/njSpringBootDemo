package com.nj.cloudalibaba.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/6 23:37
 */
public class MyNacosRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        //基本不需要实现 主要实现choose方法
    }

    @Override
    public Server choose(Object o) {
        System.out.println("实现自定义MyNacosRule负载均衡1  调用......");
        //自己对照实现类模拟实现
        ILoadBalancer lb = getLoadBalancer();
        if (lb == null){
            return null;
        }
        Server server = null;
        while (server == null){
            if (Thread.interrupted()){
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0){
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            //随机的负载均衡
            int index = new Random().nextInt(serverCount);
            server = upList.get(index);
            if (server == null){
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }
            if (server.isAlive()){
                return (server);
            }
            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }
        return server;
    }
}

package com.nj.springcloud.configer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/21 21:40
 */
@Configuration
public class SpringConfig {

    /**
     * ribbon负载均衡，默认是轮询
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
    public IRule iRule(){
        //随机负载均衡模式
//        return new RandomRule();
        //RetyRule先按照轮询策略分发，如果分发的服务不能访问，则在指定时间进行重试分发其他可用服务
        return new RetryRule();
    }
}

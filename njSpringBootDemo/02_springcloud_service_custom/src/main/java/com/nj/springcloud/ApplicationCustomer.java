package com.nj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker   //开启熔断器功能
//@SpringCloudApplication   //此注解相当于以上三个注解    EnableDiscoveryClient与EnableCircuitBreaker 前者支持多种链接或者针对eureka
public class ApplicationCustomer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCustomer.class, args);
    }

}

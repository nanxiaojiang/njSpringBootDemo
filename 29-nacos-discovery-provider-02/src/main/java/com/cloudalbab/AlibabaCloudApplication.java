package com.cloudalbab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/2 22:47
 */
@SpringBootApplication
@EnableDiscoveryClient      //注解开启服务注册与发现功能
public class AlibabaCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaCloudApplication.class,args);
    }
}

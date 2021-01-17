package com.nj.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:13
 */
@SpringBootApplication
@EnableDiscoveryClient      //开启注册
@EnableFeignClients         //开启feign
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}

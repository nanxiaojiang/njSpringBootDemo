package com.nj.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:35
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AccuntApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccuntApplication.class,args);
    }
}

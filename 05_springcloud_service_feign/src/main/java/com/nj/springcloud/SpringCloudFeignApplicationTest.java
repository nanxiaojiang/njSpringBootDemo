package com.nj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 南江
 * @date 2020/12/26 18:37
 */
@SpringBootApplication
@EnableFeignClients     //开启Spring Cloud Feign支持
public class SpringCloudFeignApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFeignApplicationTest.class);
    }
}

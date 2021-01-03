package com.nj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApplicationEurekaServer8761 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEurekaServer8761.class, args);
    }

}

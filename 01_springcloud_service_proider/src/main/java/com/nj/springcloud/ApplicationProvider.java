package com.nj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationProvider {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider.class, args);
    }

}

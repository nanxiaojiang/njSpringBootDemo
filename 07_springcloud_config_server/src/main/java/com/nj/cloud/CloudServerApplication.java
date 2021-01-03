package com.nj.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/30 22:07
 */
@EnableConfigServer
@SpringBootApplication
public class CloudServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServerApplication.class,args);
    }
}

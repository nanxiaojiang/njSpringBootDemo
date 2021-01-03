package com.nj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/26 17:22
 */
@SpringBootApplication
@EnableHystrixDashboard
public class SpringCloudHystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixDashboardApplication.class, args);
    }
}

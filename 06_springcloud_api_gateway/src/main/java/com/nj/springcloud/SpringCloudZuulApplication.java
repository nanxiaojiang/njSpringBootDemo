package com.nj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 南江
 * @Description: 使用 Zuul 构建 API 网关使用Spring boot 入口类
 * @date 2020/12/27 11:00
 */
@SpringBootApplication
@EnableZuulProxy        //开启zuul的API网关功能
public class SpringCloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulApplication.class,args);
    }
}

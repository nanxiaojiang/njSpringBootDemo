package com.nj.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author nanjang
 * @create 2021-01-05 11:36
 */
@SpringBootApplication
@EnableDiscoveryClient  //开启注册服务与发现
public class CloudAlibabaConfigApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CloudAlibabaConfigApplication.class, args);

        while (true){
            //当动态配置刷新时，会更新到 Enviroment中，因此此处每隔一秒从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            System.out.println("user name : " + userName + "; age: " + userAge);

            String currentEnv = applicationContext.getEnvironment().getProperty("current.env");
            System.err.println("in [ "+currentEnv+" ] enviroment; "+"user name :" + userName + "; age: " + userAge);
            //睡眠一秒
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
package com.nj.cloudalibaba.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author nanjang
 * @create 2021-01-05 13:45
 */
@Data       //lombok注解
@Component
@ConfigurationProperties(prefix = "user")
public class MyProperties {

    private String name;
    private String age;
}
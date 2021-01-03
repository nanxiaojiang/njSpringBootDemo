package com.nj.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/1 11:14
 */
@RestController
//@RefreshScope
public class ConfigController {

    @Value("${url}")
    private String url;

    @Autowired
    private Environment env;

    @RequestMapping("/getUrl1")
    public String getUrl1(){
        return this.url;
    }

    @RequestMapping("/getUrl2")
    public String getUrl2(){
        return env.getProperty("url");
    }
}

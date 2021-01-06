package com.nj.cloudalibaba.controller;

import com.nj.cloudalibaba.property.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:ConfigController根据nacos配置参数获取内容
 *
 * @author nanjang
 * @create 2021-01-05 13:42
 */
@RestController
public class ConfigController {

//    @Value("${user.name}")
    private String userName;

//    @Value("${user.age}")
    private String userAge;

    @Autowired
    private MyProperties myProperties;

    @RequestMapping("/getParam1")
    public String getParam1(){
        return userName + "--------------" + userAge;
    }

    @RequestMapping("/getParam2")
    public String getParam2(){
        return myProperties.getName() + "==================" + myProperties.getAge();
    }

}
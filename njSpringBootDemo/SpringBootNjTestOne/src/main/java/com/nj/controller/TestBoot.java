package com.nj.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/16 22:28
 */
@Controller
@ConfigurationProperties(prefix = "nj")
public class TestBoot {

    private String name;
    private Integer age;
    private String zl;

    @ResponseBody
    @RequestMapping("/getParam2")
    public String getParam2(){
        return "name:="+name+",age:="+age+"zl:="+zl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }
}

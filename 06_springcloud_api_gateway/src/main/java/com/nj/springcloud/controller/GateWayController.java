package com.nj.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/4 22:38
 */
@RestController
public class GateWayController {

    @RequestMapping("/api/local")
    public String hello(){

        System.out.println("在 api gateWay当中执行业务逻辑！！！");
        return "the api gateWay";
    }
}

package com.nj.springcloud.controller;

import com.nj.springcloud.service.SpringCloudFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/26 18:44
 */
@RestController
public class SpringCloudFeignController {

    @Autowired
    private SpringCloudFeignService springCloudFeignService;

    @RequestMapping("/testFeignController")
    public String testFeignController(){
        String feign = springCloudFeignService.testFeign();
        return feign;
    }
}

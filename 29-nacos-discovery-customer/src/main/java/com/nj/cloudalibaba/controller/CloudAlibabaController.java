package com.nj.cloudalibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/3 10:33
 */
@RestController
public class CloudAlibabaController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    //feign 的声明式调用
//    @Autowired
//    private EchoFeignService echoFeignService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 使用这种方式不需要@LoadBalanced
     * @param app
     * @return
     */
    @GetMapping("/echo/{app}")
    public String echoAppName(@PathVariable("app") String app){
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("29-nacos-discovery-provider");
        //  http://47.110.237.194/:18082/echo/{app}
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), app);
        System.out.println("request url:"+url);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 使用这种方式需要@LoadBalanced
     * @param str
     * @return
     */
    @GetMapping("/echo-rest/{str}")
    public String rest(@PathVariable String str) {
        //restTemplate + @LoadBalanced 实现远程调用
        return restTemplate.getForObject("http://29-nacos-discovery-provider/echo/" + str, String.class);
    }

    @RequestMapping("/getTestString")
    public String getTestString(){
        return "nanjiang";
    }

    @RequestMapping("/getTestStrin2g")
    public String getTestStrin2g(){
        return "nanjiang2";
    }
}

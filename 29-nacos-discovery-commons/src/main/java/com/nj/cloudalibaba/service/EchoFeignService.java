package com.nj.cloudalibaba.service;

import com.nj.cloudalibaba.service.configuration.FeignConfiguration;
import com.nj.cloudalibaba.service.fallback.EchoFeignServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "29-nacos-discovery-provider",
        fallbackFactory = EchoFeignServiceFallbackFactory.class,
        configuration = FeignConfiguration.class)
public interface EchoFeignService {

    @GetMapping("/echo/hello")
    default String hello() {
        return "hello";
    }

    @GetMapping("/echo/{str}")
    String echo(@PathVariable("str") String str);

    @GetMapping("/divide")
    String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

    /**
     * feign声明的接口可以有默认实现， 就是可以不需要远程服务提供者实现，自己实现了
     *
     * @param a
     * @return
     */
    default String divide(Integer a) {
        System.out.println("consumer devide method......");
        return divide(a, 1);
    }

    // restTemplate.getForObject("http://29-nacos-discovery-provider/sleep", String.class);

    // @FeignClient(name = "29-nacos-discovery-provider")
    // http://29-nacos-discovery-provider/notFound

    // http://192.168.0.104:8081/notFound
    // http://192.168.0.104:8082/notFound

    @GetMapping("/notFound")
    String notFound();
}
package com.bjpowernode.service;

import com.bjpowernode.service.configuration.FeignConfiguration;
import com.bjpowernode.service.fallback.GoodsFeignServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "29-nacos-discovery-provider",
        fallbackFactory = GoodsFeignServiceFallbackFactory.class,
        configuration = FeignConfiguration.class)
public interface GoodsFeignService {

    @RequestMapping("/goods")
    public Object getAllGoods();

}
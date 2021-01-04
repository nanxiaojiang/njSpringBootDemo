package com.nj.cloudalibaba.service;

import com.nj.cloudalibaba.service.configuration.FeignConfiguration;
import com.nj.cloudalibaba.service.fallback.GoodsFeignServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "29-nacos-discovery-provider",
        fallbackFactory = GoodsFeignServiceFallbackFactory.class,
        configuration = FeignConfiguration.class)
public interface GoodsFeignService {

    @RequestMapping("/goods")
    public Object getAllGoods();

}
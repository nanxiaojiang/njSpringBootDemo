package com.bjpowernode.service.configuration;

import com.bjpowernode.service.fallback.EchoFeignServiceFallbackFactory;
import com.bjpowernode.service.fallback.GoodsFeignServiceFallbackFactory;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {

    @Bean
    public EchoFeignServiceFallbackFactory echoFeignServiceFallbackFactory() {
        return new EchoFeignServiceFallbackFactory();
    }

    @Bean
    public GoodsFeignServiceFallbackFactory goodsFeignServiceFallbackFactory() {
        return new GoodsFeignServiceFallbackFactory();
    }
}
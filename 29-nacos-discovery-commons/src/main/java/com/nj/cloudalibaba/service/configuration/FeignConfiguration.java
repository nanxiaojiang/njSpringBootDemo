package com.nj.cloudalibaba.service.configuration;

import com.nj.cloudalibaba.service.fallback.EchoFeignServiceFallbackFactory;
import com.nj.cloudalibaba.service.fallback.GoodsFeignServiceFallbackFactory;
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
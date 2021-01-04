package com.nj.cloudalibaba.service.fallback;

import com.nj.cloudalibaba.service.EchoFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class EchoFeignServiceFallbackFactory implements FallbackFactory<EchoFeignService> {

    @Override
    public EchoFeignService create(Throwable throwable) {
        return new EchoFeignService() {
            @Override
            public String hello() {
                return "hello fall back" + throwable.getMessage();
            }

            @Override
            public String echo(@PathVariable("str") String str) {
                return "echo fallback" + throwable.getMessage();
            }

            @Override
            public String divide(@RequestParam Integer a, @RequestParam Integer b) {
                return "divide fallback" + throwable.getMessage();
            }

            @Override
            public String divide(Integer a) {
                return "divide fall back" + throwable.getMessage();
            }

            @Override
            public String notFound() {
                return "default feign invoke notFound fallback 999" + throwable.getMessage();
            }
        };
    }
}
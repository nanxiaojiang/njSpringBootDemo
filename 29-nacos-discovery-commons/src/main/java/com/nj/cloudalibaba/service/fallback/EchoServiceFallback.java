package com.nj.cloudalibaba.service.fallback;

import com.nj.cloudalibaba.service.EchoFeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/10 23:44
 */
@Component
public class EchoServiceFallback implements EchoFeignService {


    @Override
    public String echo(@PathVariable(value = "str") String str) {
        return "123";
    }

    @Override
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return "12";
    }


    @Override
    public String notFound() {
        return "default feign invoke notFound fallback";
    }
}

package com.bjpowernode.service.fallback;

import com.bjpowernode.model.Goods;
import com.bjpowernode.service.GoodsFeignService;
import feign.hystrix.FallbackFactory;

import java.util.ArrayList;

public class GoodsFeignServiceFallbackFactory implements FallbackFactory<GoodsFeignService> {

    @Override
    public GoodsFeignService create(Throwable throwable) {
        return new GoodsFeignService() {
            @Override
            public Object getAllGoods() {
                return new ArrayList<Goods>();
            }
        };
    }
}
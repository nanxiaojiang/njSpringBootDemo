package com.nj.cloudalibaba.service.fallback;

import com.nj.cloudalibaba.model.Goods;
import com.nj.cloudalibaba.service.GoodsFeignService;
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
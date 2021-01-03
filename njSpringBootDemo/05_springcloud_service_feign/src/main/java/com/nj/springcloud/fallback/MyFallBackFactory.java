package com.nj.springcloud.fallback;

import com.nj.springcloud.service.SpringCloudFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/26 19:17
 */
@Component
public class MyFallBackFactory implements FallbackFactory<SpringCloudFeignService> {
    @Override
    public SpringCloudFeignService create(Throwable throwable) {
        return new SpringCloudFeignService() {
            @Override
            public String testFeign() {
                return throwable.getMessage();
            }
        };
    }
}

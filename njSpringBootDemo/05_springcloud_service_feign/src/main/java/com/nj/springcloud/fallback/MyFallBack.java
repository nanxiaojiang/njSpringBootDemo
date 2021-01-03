package com.nj.springcloud.fallback;

import com.nj.springcloud.service.SpringCloudFeignService;
import org.springframework.stereotype.Component;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/26 19:13
 */
@Component
public class MyFallBack implements SpringCloudFeignService {
    @Override
    public String testFeign() {
        return "服务出现异常，自定义myfallback!!!";
    }
}

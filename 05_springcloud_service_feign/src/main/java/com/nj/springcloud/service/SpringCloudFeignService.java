package com.nj.springcloud.service;

import com.nj.springcloud.fallback.MyFallBack;
import com.nj.springcloud.fallback.MyFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 南江
 * @Description: 使用Feign的客户端绑定远程服务名称
 * 远程服务名称可大写 可小写
 * @date 2020/12/26 18:41
 */
@FeignClient(name = "01springcloudprovider",fallback = MyFallBack.class)//不可获取异常信息
//@FeignClient(name = "01springcloudprovider",fallbackFactory = MyFallBackFactory.class)//可获取异常信息
public interface SpringCloudFeignService {

    /**
     * 声明一个方法，这个方法就是远程服务提供者提供的那个办法
     * @return
     */
    @RequestMapping("/getProviderSpringCloud")
    public String testFeign();

}

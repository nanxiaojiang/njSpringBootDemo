package com.nj.cloudalibaba.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 0:59
 */
@FeignClient(name = "29-seata-distributed-order-service")
public interface FeignOrdersService {

    /**
     * 下订单
     * @param userId        用户ID
     * @param productId     产品ID
     * @return
     * @throws Exception
     */
    @PostMapping("/order/createOrder")
    Integer createOrder(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId);
}

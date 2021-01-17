package com.nj.cloudalibaba.controller;

import com.nj.cloudalibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南江
 * @Description: 订单controller
 * @date 2021/1/18 1:18
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public Integer createOrder(@RequestParam("userId") Integer userId,
                               @RequestParam("productId") Integer productId) throws Exception {

        log.info("请求下单, 用户:{}, 商品:{}", userId, productId);

        return orderService.createOrder(userId, productId);
    }
}

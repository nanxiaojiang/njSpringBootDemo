package com.nj.cloudalibaba.service.impl;

import com.nj.cloudalibaba.feign.FeignAccountService;
import com.nj.cloudalibaba.feign.FeignProductService;
import com.nj.cloudalibaba.mapper.OrdersMapper;
import com.nj.cloudalibaba.model.Orders;
import com.nj.cloudalibaba.model.Product;
import com.nj.cloudalibaba.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:20
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private FeignAccountService feignAccountService;

    @Autowired
    private FeignProductService feignProductService;


    @GlobalTransactional    //seata全局事务注解
    public Integer createOrder(Integer userId, Integer productId) throws Exception {
        // 购买数量暂时设置为 1
        Integer amount = 1;

        log.info("当前 XID: {}", RootContext.getXID());

        // 减库存
        Product product = feignProductService.reduceStock(productId, amount);

        // 减余额
        feignAccountService.reduceBalance(userId, product.getPrice());

        // 下订单
        Orders order = new Orders();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setPayAmount(product.getPrice().multiply(new BigDecimal(amount)));

        ordersMapper.insertSelective(order);

        log.info("下订单: {}", order.getId());
        //出异常就会回滚，如果try catch抓取异常就不会回滚
//        int a= 10/0;

        // 返回订单编号
        return order.getId();
    }
}

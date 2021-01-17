package com.nj.cloudalibaba.service;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:19
 */
public interface OrderService {

    /**
     * 下订单
     * @param userId        用户ID
     * @param productId     产品ID
     * @return
     * @throws Exception
     */
    Integer createOrder(Integer userId, Integer productId) throws Exception;
}

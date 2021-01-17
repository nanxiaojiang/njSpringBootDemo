package com.nj.cloudalibaba.service;

import com.nj.cloudalibaba.model.Product;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:55
 */
public interface ProductService {

    /**
     * 减库存
     *
     * @param productId 商品 ID
     * @param amount    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    Product reduceStock(Integer productId, Integer amount) throws Exception;
}

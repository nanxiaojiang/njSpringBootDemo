package com.nj.cloudalibaba.service.impl;

import com.nj.cloudalibaba.mapper.ProductMapper;
import com.nj.cloudalibaba.model.Product;
import com.nj.cloudalibaba.service.ProductService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:30
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product reduceStock(Integer productId, Integer amount) {
        log.info("[reduceStock] 当前 XID: {}", RootContext.getXID());

        // 检查库存
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product.getStock() < amount) {
            throw new RuntimeException("库存不足");
        }

        // 减库存
        amount = product.getStock() - amount;
        int updateCount = productMapper.reduceStock(productId, amount);
        // 减库存失败
        if (updateCount == 0) {
            throw new RuntimeException("库存不足");
        }

        // 减库存成功
        log.info("减库存 {} 库存成功", productId);
        return product;
    }
}

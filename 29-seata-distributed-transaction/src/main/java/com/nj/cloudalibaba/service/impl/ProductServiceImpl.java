package com.nj.cloudalibaba.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.nj.cloudalibaba.mapper.ProductMapper;
import com.nj.cloudalibaba.model.Product;
import com.nj.cloudalibaba.service.ProductService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:58
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @DS("product-ds")
//    @Transactional(propagation = Propagation.REQUIRES_NEW)  //开启新事务
    public Product reduceStock(Integer productId, Integer amount) throws Exception {
        log.info("当前 XID: {}", RootContext.getXID());

        // 检查库存
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product.getStock() < amount) {
            throw new Exception("库存不足");
        }

        // 扣减库存
        amount = product.getStock() - amount;
        int updateCount = productMapper.reduceStock(productId, amount);
        // 扣除成功
        if (updateCount == 0) {
            throw new Exception("库存不足");
        }

        // 扣除成功
        log.info("扣除 {} 库存成功", productId);
        return product;
    }
}

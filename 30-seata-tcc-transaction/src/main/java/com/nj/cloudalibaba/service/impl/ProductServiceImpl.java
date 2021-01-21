package com.nj.cloudalibaba.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.nj.cloudalibaba.mapper.ProductMapper;
import com.nj.cloudalibaba.model.Product;
import com.nj.cloudalibaba.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
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
    public Product reduceStock(Integer productId, Integer amount){
        log.info("当前 XID: {}", RootContext.getXID());

        // 检查库存
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product.getStock() < amount) {
            throw new RuntimeException("库存不足");
        }

        // 扣减库存
        amount = product.getStock() - amount;
        int updateCount = productMapper.reduceStock(productId, amount);
        // 扣除成功
        if (updateCount == 0) {
            throw new RuntimeException("库存不足");
        }

        // 扣除成功
        log.info("扣除 {} 库存成功", productId);
        return product;
    }

    /**
     * tcc模式的commit方法
     * 可以空确认
     * @param context   上下文
     * @return
     */
    @DS("product-ds")
    public boolean commitTcc(BusinessActionContext context) {
        log.info("Confirm阶段，ProductServiceImpl, commitTcc --> xid = " + context.getXid() + ", commitTcc提交成功");
        return true;
    }

    /**
     * tcc模式rollback方法
     * @param context    上下文
     * @return
     */
    @DS("product-ds")
    public boolean rollbackTcc(BusinessActionContext context) {
        log.info("Cancel阶段，ProductServiceImpl, cancelTcc --> xid = " + context.getXid() + ", cancelTcc提交失败");

        //TODO 这里可以实现中间件、非关系型数据库的回滚操作
        log.info("Cancel阶段，ProductServiceImpl, cancelTcc this data: {}, {}", context.getActionContext("productId"), context.getActionContext("amount"));

        //进行数据库回滚操作
        Integer productId = (Integer) context.getActionContext("productId");
        Integer amount = (Integer) context.getActionContext("amount");
        //把库存再加回去 (避免数据出问题，加个锁，分布式环境下就需要分布式锁)
        productMapper.rollbackTcc(productId,amount);
        return true;
    }
}

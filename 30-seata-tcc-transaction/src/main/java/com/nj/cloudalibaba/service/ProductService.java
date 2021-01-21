package com.nj.cloudalibaba.service;

import com.nj.cloudalibaba.model.Product;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:55
 */
@LocalTCC   //注解标识此TCC为本地模式，即该事务是本地调用
public interface ProductService {



    /**
     * 减库存
     *
     * @param productId 商品 ID
     * @param amount    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    @TwoPhaseBusinessAction(name = "reduceStock",commitMethod = "commitTcc",rollbackMethod = "rollbackTcc")
    Product reduceStock(@BusinessActionContextParameter(paramName = "productId") Integer productId,
                        @BusinessActionContextParameter(paramName = "amount") Integer amount);

    /**
     * 二阶段提交方法
     *
     * 确认方法，命名必须与commitMethod = "commitTcc"保持一致
     * ontext可以传递try方法的参数
     * @param context   上下文
     * @return
     */
    boolean commitTcc(BusinessActionContext context);

    /**
     * 二阶段回滚方法
     * @param context    上下文
     * @return
     */
    boolean rollbackTcc(BusinessActionContext context);
}

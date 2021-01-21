package com.nj.cloudalibaba.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:55
 */
@LocalTCC
public interface AccountService {

    /**
     * 减余额
     *
     * 定义两段提交
     * name = reduceStock为一阶段try方法
     * commitMethod = commitTcc 为二阶段确认方法
     * rollbackMethod = cancel 为二阶段取消方法
     * BusinessActionContextParameter注解 可传递参数到二阶段方法
     *
     * @param userId 用户id
     * @param money  扣减金额
     * @throws Exception 失败时抛出异常
     */
    @TwoPhaseBusinessAction(name = "reduceBalance",commitMethod = "commitBalance",rollbackMethod = "rollbackBalance")
    void reduceBalance(@BusinessActionContextParameter(paramName = "userId") Integer userId,
                       @BusinessActionContextParameter(paramName = "money") BigDecimal money) throws Exception;

    /**
     确认方法、可以另命名，但要保证与commitMethod一致
     * context可以传递try方法的参数
     * @param context   上下文
     * @return
     */
    boolean commitBalance(BusinessActionContext context);

    /**
     *  二阶段取消方法
     * @param context   上下文
     * @return
     */
    boolean rollbackBalance(BusinessActionContext context);
}

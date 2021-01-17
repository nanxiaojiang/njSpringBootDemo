package com.nj.cloudalibaba.service;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:55
 */
public interface AccountService {

    /**
     * 减余额
     *
     * @param userId 用户id
     * @param money  扣减金额
     * @throws Exception 失败时抛出异常
     */
    void reduceBalance(Integer userId, BigDecimal money) throws Exception;
}

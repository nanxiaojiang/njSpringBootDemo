package com.nj.cloudalibaba.service.impl;

import com.nj.cloudalibaba.mapper.AccountMapper;
import com.nj.cloudalibaba.model.Account;
import com.nj.cloudalibaba.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:38
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public void reduceBalance(Integer userId, BigDecimal money) {
        log.info("[reduceBalance] 当前 XID: {}", RootContext.getXID());

        // 检查余额
        Account account = accountMapper.selectAccountByUserId(userId);
        if (account.getBalance().doubleValue() < money.doubleValue()) {
            throw new RuntimeException("余额不足");
        }

        // 扣除余额
        int updateCount = accountMapper.reduceBalance(userId, money);
        // 扣除成功
        if (updateCount == 0) {
            throw new RuntimeException("余额不足");
        }
        log.info("[reduceBalance] 扣除用户 {} 余额成功", userId);
    }
}

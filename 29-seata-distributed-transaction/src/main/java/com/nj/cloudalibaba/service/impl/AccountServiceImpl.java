package com.nj.cloudalibaba.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.nj.cloudalibaba.mapper.AccountMapper;
import com.nj.cloudalibaba.model.Account;
import com.nj.cloudalibaba.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:57
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @DS("account-ds")
//    @Transactional(propagation = Propagation.REQUIRES_NEW)  //开启新事务
    public void reduceBalance(Integer userId, BigDecimal money) throws Exception {
        log.info("当前 XID: {}", RootContext.getXID());
        //检查余额
        Account account = accountMapper.selectAccountByUserId(userId);

        if (account.getBalance().doubleValue() < money.doubleValue()){
            throw new Exception("余额不足");
        }

        // 扣除余额
        int updateCount = accountMapper.reduceBalance(userId, money);

        if (updateCount == 0){
            throw new Exception("余额不足");
        }
        log.info("扣除用户 {} 余额成功", userId);
    }
}

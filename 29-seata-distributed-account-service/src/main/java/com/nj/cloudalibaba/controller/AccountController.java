package com.nj.cloudalibaba.controller;

import com.nj.cloudalibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:36
 */
@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/reduceBalance")
    public void reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money) throws Exception {

        log.info("[reduceBalance] 收到减少余额请求, 用户:{}, 金额:{}", userId, money);
        accountService.reduceBalance(userId, money);
    }
}

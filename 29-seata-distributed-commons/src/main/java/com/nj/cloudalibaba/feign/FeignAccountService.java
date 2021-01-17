package com.nj.cloudalibaba.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 0:58
 */
@FeignClient(name = "29-seata-distributed-account-service")
public interface FeignAccountService {

    /**
     * 减余额
     *
     * @param userId 用户id
     * @param money  扣减金额
     * @throws Exception 失败时抛出异常
     */
    @PostMapping("/account/reduceBalance")
    void reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money);
}

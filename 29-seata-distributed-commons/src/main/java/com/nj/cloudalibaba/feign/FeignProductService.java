package com.nj.cloudalibaba.feign;

import com.nj.cloudalibaba.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 0:59
 */
@FeignClient(name = "29-seata-distributed-product-service")
public interface FeignProductService {

    /**
     * 减库存
     * @param productId 商品 ID
     * @param amount    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    @PostMapping("/product/reduceStock")
    Product reduceStock(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount);
}

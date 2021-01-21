package com.nj.cloudalibaba.controller;

import com.nj.cloudalibaba.model.Product;
import com.nj.cloudalibaba.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:29
 */
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/reduceStock")
    public Product reduceStock(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount) throws Exception {

        log.info("减库存请求, 商品:{}, 价格:{}", productId, amount);
        return productService.reduceStock(productId, amount);
    }
}

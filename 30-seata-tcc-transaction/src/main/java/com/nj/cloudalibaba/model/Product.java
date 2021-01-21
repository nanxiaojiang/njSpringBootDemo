package com.nj.cloudalibaba.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:17
 */
@Data
public class Product {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Date addTime;

    private Date updateTime;
}

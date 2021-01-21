package com.nj.cloudalibaba.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:16
 */
@Data
public class Orders {

    private Integer id;
    private Integer userId;
    private Integer productId;

    private BigDecimal payAmount;

    private Date addTime;

    private Date updateTime;
}

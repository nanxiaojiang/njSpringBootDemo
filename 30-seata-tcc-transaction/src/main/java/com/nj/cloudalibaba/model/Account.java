package com.nj.cloudalibaba.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 18:15
 */
@Data
public class Account {

    private Integer id;
    private Integer userId;
    private BigDecimal balance;
    private Date updateTime;
}

package com.nj.cloudalibaba.mapper;

import com.nj.cloudalibaba.model.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/17 19:05
 */
@Mapper
public interface OrdersMapper {

    void insertSelective(Orders order);
}

package com.nj.cloudalibaba.mapper;

import com.nj.cloudalibaba.model.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:23
 */
@Mapper
public interface OrdersMapper {
    /**
     * 插入订单信息
     * @param order
     */
    void insertSelective(Orders order);
}

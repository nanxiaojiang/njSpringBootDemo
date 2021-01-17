package com.nj.cloudalibaba.mapper;

import com.nj.cloudalibaba.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:31
 */
@Mapper
public interface ProductMapper {

    Product selectByPrimaryKey(Integer productId);


    int reduceStock(@Param("productId") Integer productId, @Param("amount") Integer amount);
}

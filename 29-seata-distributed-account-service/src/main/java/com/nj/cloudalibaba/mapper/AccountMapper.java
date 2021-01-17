package com.nj.cloudalibaba.mapper;

import com.nj.cloudalibaba.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/18 1:39
 */
@Mapper
public interface AccountMapper {

    Account selectAccountByUserId(Integer userId);

    Integer reduceBalance(@Param("userId") Integer userId, @Param("money") BigDecimal money);

}

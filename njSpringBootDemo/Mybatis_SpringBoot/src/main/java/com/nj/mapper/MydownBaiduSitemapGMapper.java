package com.nj.mapper;

import com.nj.domain.MydownBiaduSitemapG;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/19 11:29
 */

@Mapper
public interface MydownBaiduSitemapGMapper {

    public List<MydownBiaduSitemapG> queryAllList();
}

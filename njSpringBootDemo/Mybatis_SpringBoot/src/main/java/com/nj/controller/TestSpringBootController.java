package com.nj.controller;

import com.nj.domain.MydownBiaduSitemapG;
import com.nj.mapper.MydownBaiduSitemapGMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/19 11:24
 */
@RestController
@Api(value = "SpringBoot整合Mybatis测试接口",tags = {"整合相关接口"})
public class TestSpringBootController {


    @Autowired
    private MydownBaiduSitemapGMapper mydownBaiduSitemapGMapper;

    @ApiOperation(value = "查询ald泛需求",notes = "查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "姓名",required = true,paramType = "query",dataType = "string"),
            @ApiImplicitParam(name = "number",value = "数量",required = true,paramType = "query",dataType = "int")
    })
//    @GetMapping("/baiduSitemapG/getQuestAll/{name}/{number}")
    @GetMapping("/baiduSitemapG/getQuestAll")
    public List<MydownBiaduSitemapG> getQuestAll(@RequestParam("name") String name,@RequestParam("number") int number,MydownBiaduSitemapG mydownBiaduSitemapG){
        List<MydownBiaduSitemapG> mydownBiaduSitemapGS = mydownBaiduSitemapGMapper.queryAllList();
        return mydownBiaduSitemapGS;
    }


    @PostMapping("/getTest")
    public String getTest(){
        return "南江测试";
    }
}

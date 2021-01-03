package com.nj.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nj.SpringBootMybatisApplication;
import com.nj.domain.MydownBiaduSitemapG;
import com.nj.mapper.MydownBaiduSitemapGMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/19 18:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMybatisApplication.class)
public class SringBootRedisTest {

    @Autowired
    private MydownBaiduSitemapGMapper mydownBaiduSitemapGMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void TestRedisSpringBoot() throws JsonProcessingException {
        //首先先从redis当中获取数据，如果为空则查询数据库在存入redis当中
        String mydownBiaduSitemapGS = redisTemplate.boundValueOps("mydownBiaduSitemapGS_njtest3").get();
        if (StringUtils.isEmpty(mydownBiaduSitemapGS)){
            //如果为空则查询数据库
            List<MydownBiaduSitemapG> mydownBiaduSitemapGList = mydownBaiduSitemapGMapper.queryAllList();
            //将数据库查到的数据转换成json格式字符串
            ObjectMapper om = new ObjectMapper();
            String value = om.writeValueAsString(mydownBiaduSitemapGList);
            //查询之后如果list得size大于0存入数据库否则输出null
            if (mydownBiaduSitemapGList.size() > 0){
                //将数据存入redis当中
                redisTemplate.boundValueOps("mydownBiaduSitemapGS_njtest3").set(value,20, TimeUnit.SECONDS);
//                redisTemplate.boundValueOps("nj").set(value,20000, TimeUnit.SECONDS);
                System.out.println("从数据库当中获取数据！！！");
            }
        }else {
            System.out.println("从redis当中获取数据！！！");
            //如果不为空则redis当中有数据 直接输出即可
        }
        System.out.println(mydownBiaduSitemapGS);
    }
}

package com.nj.test;

import com.nj.SpringBootMybatisApplication;
import com.nj.domain.MydownBiaduSitemapG;
import com.nj.mapper.MydownBaiduSitemapGMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/19 17:27
 */
//SpringRunner继承自SpringJUnit4ClassRunner,
@RunWith(SpringRunner.class)
//@SpringBootTest的属性指定的是引导类的字节码对象
@SpringBootTest(classes = SpringBootMybatisApplication.class)
public class MydownBaiduSitemapGTestController {

    @Autowired
    private MydownBaiduSitemapGMapper mydownBaiduSitemapGMapper;


    @Test
    public void TestMydown(){
        List<MydownBiaduSitemapG> mydownBiaduSitemapGS = mydownBaiduSitemapGMapper.queryAllList();
        System.out.println(mydownBiaduSitemapGS);
    }


}

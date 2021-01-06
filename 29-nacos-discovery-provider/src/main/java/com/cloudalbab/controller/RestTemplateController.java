package com.cloudalbab.controller;

import com.cloudalbab.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *
 * @author nanjang
 * @create 2021-01-06 15:52
 */
@RestController
public class RestTemplateController {

    @RequestMapping("/service/hello")
    public String hello(HttpServletRequest request){
        String header = request.getHeader("X-Request-Id");

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/hello -->" + header);
        return "Hello, Spring Cloud，Provider";
    }

    @RequestMapping("/service/user")
    public User user(){

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/user");
        User user = new User();
        user.setId(10001);
        user.setName("providernj");
        user.setPhone("17791412622");

        return user;
    }

    @RequestMapping("/service/getUser")
    public User getUser(@RequestParam(value = "id") Integer id,
                        @RequestParam(value = "name") String name,
                        @RequestParam(value = "phone") String phone){

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/getUser");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        return user;
    }

    @PostMapping("/service/addUser")
    public User addUser(@RequestParam(value = "id") Integer id,
                        @RequestParam(value = "name") String name,
                        @RequestParam(value = "phone") String phone){

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/addUser");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        return user;
    }

    @PostMapping("/service/addUser2")
    public User addUser2(@RequestBody User user,@RequestParam(value = "token")String token,@RequestParam(value = "encode")String encode){
        //进行业务处理（省略）
        System.out.println(token + "---" + encode);
        System.out.println("服务提供者1-->/service/addUser2-->" + user.getId() + "--" + user.getName() + "--" + user.getPhone());

        //将user对象插入数据库（暂时省略）
        return user;
    }

    @PostMapping("/service/addUser3")
    public User addUser3(@RequestBody User user){
        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/addUser3-->" + user.getId() + "--" + user.getName() + "--" + user.getPhone());

        //将user对象插入数据库（暂时省略）
        return user;
    }
}
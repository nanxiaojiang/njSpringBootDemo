package com.nj.cloudalibaba.controller;

import com.nj.cloudalibaba.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 *
 * @author nanjang
 * @create 2021-01-06 15:54
 */
@RestController
public class RestTemplateController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.name}")
    private String serviceName;

    /**
     * Get请求，无参数，返回String
     * @return
     */
    @RequestMapping("/web/hello")
    public String hello(){

        //业务逻辑判断处理省略

        //调用SpringCloud服务提供者提供的服务
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(serviceName + "/service/hello", String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println(statusCode);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        System.out.println(statusCodeValue);
        HttpHeaders headers = responseEntity.getHeaders();
        System.out.println(headers);
        String body = responseEntity.getBody();
        System.out.println(body);
        return restTemplate.getForEntity(serviceName + "/service/hello", String.class).getBody();
    }

    /**
     * Get请求，无参数，返回User
     * @return
     */
    @RequestMapping("/web/user")
    public User user(){

        //业务逻辑判断处理省略

        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity(serviceName + "/service/user", User.class);
        HttpStatus statusCode = userResponseEntity.getStatusCode();
        System.out.println(statusCode);
        int statusCodeValue = userResponseEntity.getStatusCodeValue();
        System.out.println(statusCodeValue);
        HttpHeaders headers = userResponseEntity.getHeaders();
        System.out.println(headers);
        User body = userResponseEntity.getBody();
        System.out.println(body);
        return restTemplate.getForEntity(serviceName + "/service/user", User.class).getBody();
    }

    /**
     * Get请求，有参数，返回User
     * @return
     */
    @RequestMapping("/web/getUser")
    public User getUser(){

        //业务逻辑判断处理省略
        //定义数组
        String[] userArry = {"101","nanjiang","18821611462"};
        //定义map
        Map<String, Object> paramMap = new ConcurrentHashMap<>();
        paramMap.put("id",102);
        paramMap.put("name","wuxiao");
        paramMap.put("phone","18992384582");

        //调用SpringCloud服务提供者提供的服务
        //参数为数组
        ResponseEntity<User> userResponseEntity1 = restTemplate.getForEntity(serviceName + "/service/getUser?id={0}&name={1}&phone={2}", User.class, userArry);
        //参数为map
        ResponseEntity<User> userResponseEntity2 = restTemplate.getForEntity(serviceName + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, paramMap);


        User user1 = restTemplate.getForObject(serviceName + "/service/getUser?id={0}&name={1}&phone={2}", User.class, userArry);
        System.out.println(user1.getId() + "------" + user1.getName() + "------" + user1.getPhone());
        System.out.println("马上出处user2");
        User user2 = restTemplate.getForObject(serviceName + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, paramMap);
        System.out.println(user2.getId() + "======" + user2.getName() + "======" + user2.getPhone());


        return restTemplate.getForEntity(serviceName + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, paramMap).getBody();
    }

    /**
     * POST请求，有参数，返回User -addUser方法
     * @return
     */
    @RequestMapping("/web/addUser")
    public User addUser(){

        //业务逻辑判断处理省略
        //定义数组
        String[] userArry = {"2001","nj","200120012001"};
        //要传的表单信息，参数数据（很坑人的），用普通HashMap包装参数，传不过去
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("id",2002);
        paramMap.add("name","wx");
        paramMap.add("phone","200220022002");
        //调用postForEntity
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity(serviceName + "/service/addUser", paramMap, User.class);
        HttpStatus statusCode = userResponseEntity.getStatusCode();
        System.out.println(statusCode);
        int statusCodeValue = userResponseEntity.getStatusCodeValue();
        System.out.println(statusCodeValue);
        HttpHeaders headers = userResponseEntity.getHeaders();
        System.out.println(headers);
        User user = userResponseEntity.getBody();
        System.out.println(user.getId() + "------" + user.getName() + "------" + user.getPhone());
        //调用postForObject
        User user2 = restTemplate.postForObject(serviceName + "/service/addUser", paramMap, User.class);
        System.out.println(user2.getId() + "======" + user2.getName() + "======" + user2.getPhone());

        //传递User对象
        User user3 = new User();
        user3.setId(2003);
        user3.setName("nxj");
        user3.setPhone("200320032003");
        User user4 = restTemplate.postForObject(serviceName + "/service/addUser2?token={token}&encode={encode}", user3, User.class,"123456","utf-8");
        System.out.println(user4.getId() + "******" + user4.getName() + "******" + user4.getPhone());

        //传递JSON值调用postForObject
        String userJSON = "{\"id\" : 1088, \"name\" : \"殷素素\", \"phone\" : \"13900000000\"}";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(userJSON, headers);
        User user5 = restTemplate.postForObject(serviceName + "/service/addUser3", entity, User.class);
        System.out.println(user5.getId() + "-*-*-*" + user5.getName() + "-*-*-*" + user5.getPhone());

        return restTemplate.postForObject(serviceName + "/service/addUser", paramMap, User.class);
    }

}
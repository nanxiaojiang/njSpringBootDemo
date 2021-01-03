package com.nj.springcloud.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.nj.springcloud.common.MyHystrixCommand;
import com.nj.springcloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/21 21:38
 */
@RestController
public class SpringCloudCustomer {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getCustomer")
    public String getCustomer(){
//        String body = restTemplate.getForEntity("http://localhost:8081/getProviderSpringCloud", String.class).getBody();
        String body = restTemplate.getForEntity("http://01springcloudprovider/getProviderSpringCloud", String.class).getBody();
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://01SPRINGCLOUDPROVIDER/getProviderSpringCloud", String.class);
//        String body = responseEntity.getBody();
//        System.out.println(responseEntity.getBody());
//        System.out.println(responseEntity.getHeaders());
//        System.out.println(responseEntity.getStatusCode());
//        System.out.println(responseEntity.getStatusCodeValue());
        return body;
    }

    /**
     * 两种传参方式，一种数组  一种map
     * @return
     */
    @RequestMapping("/getUser")
    public User getUser(){
        String[] str = {"1001","w吴笑","大荔"};
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("id","1002");
        map.put("name","南江");
        map.put("address","礼泉");
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("http://01SPRINGCLOUDPROVIDER/getProviderSpringCloudUser?id={1}&name={2}&address={3}", User.class,str);
        User user1 = restTemplate.getForEntity("http://01SPRINGCLOUDPROVIDER/getProviderSpringCloudUser?id={id}&name={name}&address={address}", User.class,map).getBody();
        User user2 = restTemplate.getForObject("http://01SPRINGCLOUDPROVIDER/getProviderSpringCloudUser?id={id}&name={name}&address={address}", User.class,map);
        System.out.println(user2);
        User user = userResponseEntity.getBody();
        return user;
    }

    @RequestMapping("/addUser")
    public User addUser(){
        String[] str = {"1001","w吴笑","大荔"};
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("id","1002");
        map.put("name","南江");
        map.put("address","礼泉");
        MultiValueMap dataMap = new LinkedMultiValueMap();
        dataMap.add("id","1003");
        dataMap.add("name","wuw");
        dataMap.add("address","shanxi");
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity("http://01SPRINGCLOUDPROVIDER/addProviderSpringCloudUser", dataMap, User.class);
        User user = userResponseEntity.getBody();
        return user;
    }

    @RequestMapping("/putUser")
    public String putUser(){
        String[] str = {"1001","w吴笑","大荔"};
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("id","1002");
        map.put("name","南江");
        map.put("address","礼泉");
        MultiValueMap dataMap = new LinkedMultiValueMap();
        dataMap.add("id","1004");
        dataMap.add("name","wuw2");
        dataMap.add("address","shanxi2");

        restTemplate.put("http://01SPRINGCLOUDPROVIDER/putProviderSpringCloudUser",dataMap);
        return "successuppdateUser";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(){
        String[] str = {"1001","w吴笑","大荔"};
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put("id","1002");
        map.put("name","南江");
        map.put("address","礼泉");
        restTemplate.delete("http://01SPRINGCLOUDPROVIDER/deleteProviderSpringCloudUser?id={1}&name={2}&address={3}",str);
        return "successdeleteUser";
    }

    @HystrixCommand(fallbackMethod = "error",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    @RequestMapping("/testHystrix")//熔断器调用不通回调error方法
    public String testHystrix(){
//        int a =10/0;
        String body = restTemplate.getForEntity("http://01SPRINGCLOUDPROVIDER/getProviderSpringCloud", String.class).getBody();
        return body;
    }

    public String error(Throwable throwable){
        System.out.println(throwable.getMessage());
        System.out.println(throwable.getStackTrace());
        return "hystrixError";
    }
    @RequestMapping("/testHystrix2")//熔断器调用不通回调error方法
    public String testHystrix2() throws ExecutionException, InterruptedException {
        String str = "";
        MyHystrixCommand myHystrixCommand = new MyHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);

        //同步调用方法(该方法执行后，会等待远程的返回结果，拿到远程的返回结果，该方法才返回然后继续向下执行)
//        str = (String) myHystrixCommand.execute();
//        System.out.println(str);
        //一部调用（该方法执行后，不会马上有执行结果，将来会有）
        Future<String> future = myHystrixCommand.queue();
        str = future.get();
        System.out.println(str);
        return str;
    }


}

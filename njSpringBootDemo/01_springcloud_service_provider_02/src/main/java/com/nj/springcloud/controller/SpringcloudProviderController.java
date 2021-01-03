package com.nj.springcloud.controller;

import com.nj.springcloud.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/21 21:24
 */
@RestController
public class SpringcloudProviderController {


    @RequestMapping("/getProviderSpringCloud")
    public String getProviderSpringCloud(){
        int number = 10/0;
        return "providerSpringCloud9200!!!";
    }

    @GetMapping("/getProviderSpringCloudUser")
    public User getProviderSpringCloudUser(int id, String name, String address){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        return user;
    }

    @PostMapping("/addProviderSpringCloudUser")
    public User addProviderSpringCloudUser(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name
            , @RequestParam(value = "address") String address){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        return user;
    }

    @PutMapping("/putProviderSpringCloudUser")
    public User putProviderSpringCloudUser(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name
            , @RequestParam(value = "address") String address){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        System.out.println(user);
        return user;
    }

    @DeleteMapping("/deleteProviderSpringCloudUser")
    public User deleteProviderSpringCloudUser(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name
            , @RequestParam(value = "address") String address){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        System.out.println(user);
        return user;
    }
}

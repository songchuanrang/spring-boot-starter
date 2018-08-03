package com.baidu.springbootstarter.controller;

import com.baidu.springbootstarter.model.User;
import com.baidu.springbootstarter.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index/{id}")
    public User index(@PathVariable int id) {
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "/sendById/{id}")
    public User sendById(@PathVariable int id) {
        User user = userService.findById(id);
        userService.save(user);
        return user;
    }

    @RequestMapping(value = "/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/helloworld")
    public String helloworld() {
        return "Hello World!";
    }

}

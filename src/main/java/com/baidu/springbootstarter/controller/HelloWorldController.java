package com.baidu.springbootstarter.controller;

import com.baidu.springbootstarter.model.User;
import com.baidu.springbootstarter.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index/{id}")
    public User index(@ModelAttribute User user) {
        System.out.println(user.getId());
        userService.save(user);
        return user;
    }

    @RequestMapping("/helloworld")
    public String helloworld() {
        return "Hello World!";
    }

}

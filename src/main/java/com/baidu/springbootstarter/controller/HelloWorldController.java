package com.baidu.springbootstarter.controller;

import com.baidu.springbootstarter.model.User;
import com.baidu.springbootstarter.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    public String index(@PathVariable String id, @ModelAttribute User user, @RequestParam String pwd) {
        System.out.println(user.getId());
        System.out.println(pwd);
        userService.save(user);
        return id;
    }

    @RequestMapping("/helloworld")
    public String helloworld() {
        return "Hello World!";
    }

}

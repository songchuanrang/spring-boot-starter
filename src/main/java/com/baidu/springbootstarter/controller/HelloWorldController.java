package com.baidu.springbootstarter.controller;

import com.baidu.springbootstarter.common.CommonException;
import com.baidu.springbootstarter.common.Result;
import com.baidu.springbootstarter.model.User;
import com.baidu.springbootstarter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/hello")
@Api("lala")
public class HelloWorldController {

    @Resource
    private UserService userService;

    @ApiOperation("aa")
    @GetMapping(value = "/index/{id}")
    public User index(@PathVariable int id) {
        User user = userService.findById(id);
        return user;
    }

    @GetMapping(value = "/sendById/{id}")
    public User sendById(@PathVariable int id) {
        User user = userService.findById(id);
        userService.save(user);
        return user;
    }

    @GetMapping(value = "/findAll")
    public Result<List<User>> findAll() {
        throw new CommonException(Result.FAIL_CODE, "参数异常！");
//        return Result.SUCCESS.res(userService.findAll());
    }

    @GetMapping("/helloworld")
    public String helloworld() {
        return "Hello World!";
    }

}

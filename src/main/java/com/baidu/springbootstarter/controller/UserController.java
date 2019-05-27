package com.baidu.springbootstarter.controller;

import com.baidu.springbootstarter.common.CommonController;
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
@RequestMapping("/user")
@Api(tags = "用户信息")
public class UserController extends CommonController {

    @Resource
    private UserService userService;

    @ApiOperation("根据id查询用户")
    @GetMapping(value = "/findById/{id}")
    public Result<User> findById(@PathVariable int id) {
        return sendSuccess(userService.findById(id));
    }

    @ApiOperation("查询所有用户")
    @GetMapping(value = "/findAll")
    public Result<List<User>> findAll() {
        return sendSuccess(userService.findAll());
    }
}

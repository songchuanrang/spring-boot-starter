package com.baidu.springbootstarter.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息")
public class User {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("用户名")
    private String username;
    @JSONField(serialize = false)
    private String password;
    @ApiModelProperty("年龄")
    private Integer age;
}

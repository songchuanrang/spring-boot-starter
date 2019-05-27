package com.baidu.springbootstarter.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    @JSONField(serialize = false)
    private String password;
    private Integer age;
}

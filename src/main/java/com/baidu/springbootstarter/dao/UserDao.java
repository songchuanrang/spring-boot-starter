package com.baidu.springbootstarter.dao;

import com.baidu.springbootstarter.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User findById(@Param("id") int id);
    List<User> findAll();
}

package com.baidu.springbootstarter.service.impl;

import com.baidu.springbootstarter.dao.UserDao;
import com.baidu.springbootstarter.model.User;
import com.baidu.springbootstarter.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.baidu.springbootstarter.util.Constants.EXCHANGE_NAME_USER;
import static com.baidu.springbootstarter.util.Constants.ROUTING_KEY_USER;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private UserDao userDao;

    @Override
    public void save(User user){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME_USER, ROUTING_KEY_USER, user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

}

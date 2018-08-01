package com.baidu.springbootstarter.service.impl;

import com.baidu.springbootstarter.model.User;
import com.baidu.springbootstarter.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.baidu.springbootstarter.util.Constants.EXCHANGE_NAME;
import static com.baidu.springbootstarter.util.Constants.ROUTING_KEY;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void save(User user){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, user);
    }
}

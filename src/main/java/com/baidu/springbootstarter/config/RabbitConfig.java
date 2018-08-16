package com.baidu.springbootstarter.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

import static com.baidu.springbootstarter.util.Constants.*;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitAdmin rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
    }

    @Bean
    public Exchange userExchange() {
        return new DirectExchange(EXCHANGE_NAME_USER);
    }
    @Bean
    public Queue queueUser() {
        return new Queue(QUEUE_NAME_USER, true);
    }
    @Bean
    public Binding bindingUser() {
        return BindingBuilder.bind(queueUser()).to(userExchange()).with(ROUTING_KEY_USER).noargs();
    }

    @Bean
    public Queue queueUserRetry() {
        return new Queue(QUEUE_NAME_USER_RETRY, true);
    }
    @Bean
    public Binding bindingUserRetry() {
        return BindingBuilder.bind(queueUserRetry()).to(userExchange()).with(ROUTING_KEY_USER).noargs();
    }
/*
    @Bean
    public DirectExchange userRetryExchange() {
        return new DirectExchange(EXCHANGE_NAME_USER_RETRY);
    }
    @Bean
    public Queue queueRetryUser() {
        Map<String, Object> args = new HashMap();
        args.put("x-message-ttl", 100000);
        args.put("x-dead-letter-exchange", EXCHANGE_NAME_USER);
        return new Queue(QUEUE_NAME_USER_RETRY, true, false, false, args);
    }
    @Bean
    public Binding bindingUserRetry() {
        return BindingBuilder.bind(queueUser()).to(userRetryExchange()).with(ROUTING_KEY_USER_RETRY);
    }

    @Bean
    public DirectExchange userFailedExchange() {
        return new DirectExchange(EXCHANGE_NAME_USER_FAILED);
    }
    @Bean
    public Queue queueUserFailed() {
        return new Queue(QUEUE_NAME_USER_FAILED, true);
    }
    @Bean
    public Binding bindingUserFailed() {
        return BindingBuilder.bind(queueUser()).to(userFailedExchange()).with(ROUTING_KEY_USER_FAILED);
    }

*/
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME_USER);
        container.setMessageListener((Message message) -> {
            MessageProperties messageProperties = message.getMessageProperties();
            try {
                String body = new String(message.getBody(), messageProperties.getContentEncoding());
                System.out.println(body);
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }

        });
        return container;
    }
}

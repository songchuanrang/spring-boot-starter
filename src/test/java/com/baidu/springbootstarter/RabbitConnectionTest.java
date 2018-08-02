package com.baidu.springbootstarter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitConnectionTest {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("rabbit_user");
        factory.setPassword("rabbit_password");
        factory.setHost("47.52.97.135");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("QUEUE_NAME", true, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("EXCHANGE_NAME", "ROUTING_KEY", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }

}

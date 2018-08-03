package com.baidu.springbootstarter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.baidu.springbootstarter.util.Constants.*;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@MapperScan("com.baidu.springbootstarter.dao")
public class SpringBootApplicationInitializer extends SpringBootServletInitializer {

    @Value("${spring.rabbitmq.host}")
    private String mqRabbitHost;
    @Value("${spring.rabbitmq.port}")
    private int mqRabbitPort;
    @Value("${spring.rabbitmq.username}")
    private String mqRabbitUserName;
    @Value("${spring.rabbitmq.password}")
    private String mqRabbitPassword;
    @Value("${spring.rabbitmq.virtual-host}")
    private String mqRabbitVirtualHost;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean mqRabbitPublisherConfirms;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationInitializer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootApplicationInitializer.class);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.mqRabbitHost, this.mqRabbitPort);

        connectionFactory.setUsername(this.mqRabbitUserName);
        connectionFactory.setPassword(this.mqRabbitPassword);
        connectionFactory.setVirtualHost(this.mqRabbitVirtualHost);
        connectionFactory.setPublisherConfirms(mqRabbitPublisherConfirms);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTING_KEY);
    }

    /*@Bean
    public SimpleMessageListenerContainer messageContainer() {
        this.logger = LogFactory.getLog(getClass());

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {

            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
                byte[] body = message.getBody();
                logger.info("消费端接收到消息 : " + new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });
        return container;
    }*/

}

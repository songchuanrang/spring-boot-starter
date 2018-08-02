package com.baidu.springbootstarter.receiver;

import com.baidu.springbootstarter.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.baidu.springbootstarter.util.Constants.QUEUE_NAME;

@Component
@RabbitListener(queues = QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
public class HelloReceiver {

    private Log logger = LogFactory.getLog(getClass());

    @RabbitHandler()
    public void process(User user) {
        logger.info("Receiver : " + user);
    }

}
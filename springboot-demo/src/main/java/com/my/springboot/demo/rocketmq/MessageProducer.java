package com.my.springboot.demo.rocketmq;

import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }

    public void sendMessage(String topic, RocketMessage rocketMessage) {
        rocketMQTemplate.convertAndSend(topic, rocketMessage);
    }
}

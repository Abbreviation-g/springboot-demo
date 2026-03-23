package com.my.springboot.demo.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "springboot-demo-topic-string", consumerGroup = "springboot-demo-consumer-string")
public class MessageConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("mqtt received string message: {}", message);
    }
}

package com.my.springboot.demo.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "springboot-demo-topic", consumerGroup = "springboot-demo-consumer")
public class MessageConsumer3 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("mqtt received string message springboot-demo-topic: {}", message);
    }
}

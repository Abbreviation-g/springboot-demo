package com.my.springboot.demo.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "springboot-demo-topic-object", consumerGroup = "springboot-demo-consumer-object")
public class MessageConsumer2 implements RocketMQListener<RocketMessage> {
    @Override
    public void onMessage(RocketMessage message) {
        log.info("mqtt received object message: {}", message);
    }
}

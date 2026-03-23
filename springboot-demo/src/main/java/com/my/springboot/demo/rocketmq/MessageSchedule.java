package com.my.springboot.demo.rocketmq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MessageSchedule {
    @Resource
    private MessageProducer messageProducer;

    /**
     * 每天24点执行一次
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void run() {
        log.info("mqtt定时任务开始执行... date:{}", new Date());
        messageProducer.sendMessage("springboot-demo-topic-string", "定时任务执行时间: " + new Date());
        RocketMessage message = RocketMessage.builder().
                id(1111L).
                message("hello,world")
                .localDate(LocalDate.now())
                .localDateTime(LocalDateTime.now())
                .build();
        messageProducer.sendMessage("springboot-demo-topic-object", message);
    }
}

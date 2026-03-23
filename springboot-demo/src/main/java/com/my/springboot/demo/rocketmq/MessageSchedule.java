package com.my.springboot.demo.rocketmq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MessageSchedule {
    @Resource
    private MessageProducer messageProducer;
    /**
     * 每1分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void run() {
        log.info("定时任务开始执行... date:{}", new Date());
        System.out.println("定时任务开始执行...");

        messageProducer.sendMessage("springboot-demo-topic", "定时任务执行时间: " + new Date());
    }
}

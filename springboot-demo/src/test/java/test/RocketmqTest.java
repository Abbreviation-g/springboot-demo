package test;

import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.rocketmq.MessageProducer;
import com.my.springboot.demo.rocketmq.RocketMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocketmqTest {
    @Resource
    private MessageProducer messageProducer;

    @Test
    public void run() {
        messageProducer.sendMessage("springboot-demo-topic-string", "执行时间: " + new Date());
        RocketMessage message = RocketMessage.builder().id(1111L).message("hello,world").localDate(LocalDate.now()).localDateTime(LocalDateTime.now()).build();
        messageProducer.sendMessage("springboot-demo-topic-object", message);
    }
}

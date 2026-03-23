package test;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class RocketmqTest2 {

    public static void main(String[] args) {
        test();
//        test2();
//        sendMessage();
//        consumeMessage();
    }

    public static void test2() {
        try {
            CompletableFuture.runAsync(()->{
                System.out.println("test2 run 1");
            });
            CompletableFuture.runAsync(()->{
                System.out.println("test2 run 2");
            });
        } catch (Exception ex) {
            log.error("CompletableFuture error", ex);
        }
    }

    public static void test() {
        try {
            sendMessage();
//            CompletableFuture.runAsync(RocketmqTest2::sendMessage);
            CompletableFuture.runAsync(RocketmqTest2::consumeMessage).get();
        } catch (Exception ex) {
            log.error("CompletableFuture error", ex);
        }

//        new Thread(() -> {
//            sendMessage();
//        }).start();
//
//        new Thread(() -> {
//            consumeMessage();
//        }).start();
    }

    public static void sendMessage() {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("producer_group");
            producer.setNamesrvAddr("172.16.31.99:9876");
            producer.start();

            new Timer(true).schedule(new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    try {
                        // 创建消息
                        Message message = new Message("springboot-demo-topic", "tag", (new Date() + "Hello RocketMQ").getBytes());
                        // 发送消息并等待响应
                        SendResult result = producer.send(message);
                        // 打印发送结果
                        System.out.println("发送结果：" + result.getSendStatus());
                    } catch (Exception ex) {
                        log.error("send message error", ex);
                    } finally {
                        count++;
                        if (count >= 3) {
                            this.cancel();
                            producer.shutdown();
                        }
                    }
                }
            }, 1000 * 2, 1000);
        } catch (Exception ex) {
            log.error("send message error", ex);
        }
    }

    public static void consumeMessage() {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group");
            consumer.setNamesrvAddr("172.16.31.99:9876");

            consumer.subscribe("springboot-demo-topic", "*");
            // 注册消息监听器
            consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
                for (MessageExt msg : msgs) {
                    log.info("consumer_group, topic={}, msg={}", msg.getTopic(), new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            });

            consumer.start();
        } catch (Exception ex) {
            log.error("consume message error", ex);
        }
    }
}

package com.my.springboot.demo.eventlistener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {
    @EventListener
    public void handleEvent(MyEvent event) {
        System.out.println("接收到事件：" + event.getSource());
    }
}

package com.my.springboot.demo.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

@Component
@Slf4j
public class TextWebSocketHandlerV2 extends AbstractWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("TextWebSocketHandlerV2, session={}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        log.info("TextWebSocketHandlerV2, message={}", message.getPayload());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message.getPayload());
        jsonObject.put("time", System.currentTimeMillis());
        jsonObject.put("from", "v2");
        session.sendMessage(new TextMessage(jsonObject.toJSONString()));
    }
}

package com.my.springboot.demo.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class BinaryWebSocketHandlerV2 extends AbstractWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("BinaryWebSocketHandlerV2, session={}", session.getId());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        byte[] array = message.getPayload().array();
        log.info("BinaryWebSocketHandlerV2, array.length={}", array.length);
        String parsedStr = new String(array, StandardCharsets.UTF_8);
        session.sendMessage(new TextMessage(parsedStr));
    }
}

package com.my.springboot.demo.websocket;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class BinaryWebSocketHandlerV1 extends AbstractWebSocketHandler {
    @Setter
    private OkHttpClient okHttpClient;

    private final Map<WebSocketSession, WebSocket> sourceTargetMap = new ConcurrentHashMap<>();
    private final Map<WebSocket, WebSocketSession> targetSourceMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("BinaryWebsocketV1, session={}", session.getId());

        if (!sourceTargetMap.containsKey(session)) {
            Request request = new Request.Builder().url("ws://127.0.0.1:8763/websocket/binary/v2").build();
            WebSocket ws = okHttpClient.newWebSocket(request, new MyBinaryWebSocketListener());
            sourceTargetMap.put(session, ws);
            targetSourceMap.put(ws, session);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("BinaryWebsocketV1, session={}, status={}", session.getId(), status);
        if (sourceTargetMap.containsKey(session)) {
            WebSocket target = sourceTargetMap.get(session);
            target.close(1000, "Goodbye, WebSocket!");
            sourceTargetMap.remove(session);
            targetSourceMap.remove(target);
        }
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        byte[] data = message.getPayload().array();
        log.info("BinaryWebsocketV1, data.length={}", data.length);

        if (sourceTargetMap.containsKey(session)) {
            WebSocket target = sourceTargetMap.get(session);
            ByteString sendData = ByteString.of(data);
            target.send(sendData);
        }
    }

    private class MyBinaryWebSocketListener extends okhttp3.WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            log.info("MyBinaryWebSocketListener, onOpen");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            log.info("MyBinaryWebSocketListener, onMessage, text={}", text);
            if (targetSourceMap.containsKey(webSocket)) {
                WebSocketSession source = targetSourceMap.get(webSocket);
                try {
                    source.sendMessage(new TextMessage(text));
                } catch (IOException e) {
                    log.error("send message error", e);
                }
            }
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            log.info("MyBinaryWebSocketListener, onClosing, code={}, reason={}", code, reason);
        }
    }
}

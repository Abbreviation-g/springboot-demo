package com.my.springboot.demo.websocket;

import jakarta.annotation.Resource;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okio.ByteString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSocket
public class WebSocketHandlerConfig implements WebSocketConfigurer {
    @Resource
    private TextWebSocketHandlerV1 handlerV1;
    @Resource
    private TextWebSocketHandlerV2 handlerV2;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        OkHttpClient webSocketClient = webSocketClient();
        handlerV1.setOkHttpClient(webSocketClient);

        registry.addHandler(handlerV1, "/websocket/v1")
                .setAllowedOrigins("*");
        registry.addHandler(handlerV2, "/websocket/v2")
                .setAllowedOrigins("*");
    }

    public OkHttpClient webSocketClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(30, 100, TimeUnit.SECONDS))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        okHttpClient.dispatcher().setMaxRequestsPerHost(150);
        okHttpClient.dispatcher().setMaxRequests(150);
        return okHttpClient;
    }
}
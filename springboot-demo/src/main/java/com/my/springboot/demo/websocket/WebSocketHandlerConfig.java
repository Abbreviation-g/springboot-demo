package com.my.springboot.demo.websocket;

import jakarta.annotation.Resource;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Configuration;
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
    @Resource
    private BinaryWebSocketHandlerV1 handlerBinaryV1;
    @Resource
    private BinaryWebSocketHandlerV2 handlerBinaryV2;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        OkHttpClient webSocketClient = webSocketClient();
        handlerV1.setOkHttpClient(webSocketClient);

        registry.addHandler(handlerV1, "/websocket/v1")
                .setAllowedOrigins("*");
        registry.addHandler(handlerV2, "/websocket/v2")
                .setAllowedOrigins("*");

        handlerBinaryV1.setOkHttpClient(webSocketClient);
        registry.addHandler(handlerBinaryV1, "/websocket/binary/v1")
                .setAllowedOrigins("*");
        registry.addHandler(handlerBinaryV2, "/websocket/binary/v2")
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
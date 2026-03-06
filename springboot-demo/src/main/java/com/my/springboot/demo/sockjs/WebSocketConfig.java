package com.my.springboot.demo.sockjs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册 SockJS 端点，允许客户端通过 /ws 连接
        registry.addEndpoint("/sockjs")
                .setAllowedOriginPatterns("*")
                .withSockJS();  // 启用 SockJS 降级支持
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 启用简单的内存消息代理，用于广播消息
        registry.enableSimpleBroker("/topic");
        // 客户端发送消息的前缀
        registry.setApplicationDestinationPrefixes("/app");
    }
}

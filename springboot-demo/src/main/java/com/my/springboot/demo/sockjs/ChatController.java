package com.my.springboot.demo.sockjs;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload String message, SimpMessageHeaderAccessor headerAccessor) {
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        messagingTemplate.convertAndSend("/topic/messages", new ChatMessage(username, message, LocalDateTime.now()));
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload String username, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", username);
        messagingTemplate.convertAndSend("/topic/messages", new ChatMessage("SYSTEM", username + " 加入了聊天室", LocalDateTime.now()));
    }
}

package com.my.springboot.demo.sockjs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String sender;
    private String content;
    private LocalDateTime timestamp;
}

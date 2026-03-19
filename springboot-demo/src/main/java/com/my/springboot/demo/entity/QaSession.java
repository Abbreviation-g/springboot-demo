package com.my.springboot.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class QaSession implements Serializable {
    private String sessionId;
    private String userId;
    private LocalDateTime initialRequestTime;
    private String initialQuestion;
    private String initialAnswer;
    private LocalDateTime latestRequestTime;
    private String latestQuestion;
    private String latestAnswer;
}

package com.my.springboot.demo.rocketmq;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class RocketMessage {
    private Long id;
    private String message;
    private LocalDate localDate;
    private LocalDateTime localDateTime;

    public RocketMessage() {
    }

    public RocketMessage(Long id, String message, LocalDate localDate, LocalDateTime localDateTime) {
        this.id = id;
        this.message = message;
        this.localDate = localDate;
        this.localDateTime = localDateTime;
    }
}
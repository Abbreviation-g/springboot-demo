package com.my.springboot.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {
    private List<CustomData> data;
    private String name;
    private String value;

    @Setter
    @Getter
    public static class CustomData{
        private String name;
        private String value;
    }
}

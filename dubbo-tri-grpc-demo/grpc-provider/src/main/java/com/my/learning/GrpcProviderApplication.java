package com.my.learning;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// jvm options
// --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.math=ALL-UNNAMED
@SpringBootApplication
@EnableDubbo
public class GrpcProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrpcProviderApplication.class, args);
    }
}

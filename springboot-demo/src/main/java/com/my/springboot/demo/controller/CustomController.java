package com.my.springboot.demo.controller;

import com.my.springboot.demo.config.CustomConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/custom")
public class CustomController {
    @Resource
    private CustomConfig customConfig;

    @GetMapping
    public String custom() {
        log.info("customConfig: {}", customConfig);
        return "custom";
    }
}

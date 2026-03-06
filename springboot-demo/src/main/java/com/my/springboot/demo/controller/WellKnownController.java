package com.my.springboot.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WellKnownController {
    @RequestMapping("/.well-known/**")
    public ResponseEntity<Void> handleWellKnown() {
        // 返回404
        return ResponseEntity.notFound().build();
    }
}
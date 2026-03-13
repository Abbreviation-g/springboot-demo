package com.my.springboot.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping("/sse3")
@RestController
public class Sse3Controller {

    @GetMapping("/forward")
    public ResponseBodyEmitter forward() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        WebClient webclient = WebClient.create();
        CompletableFuture.runAsync(() -> {
            try {
                webclient.get()
                        .uri("http://127.0.0.1:8763/sse3/stream")
                        .retrieve()
                        .bodyToFlux(byte[].class)
                        .subscribe(bytes -> {
                                    try {
                                        emitter.send(bytes, MediaType.APPLICATION_OCTET_STREAM);
                                    } catch (Exception e) {
                                        log.error("", e);
                                        emitter.completeWithError(e);
                                    }
                                },
                                emitter::completeWithError,
                                emitter::complete);
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @GetMapping("/forwardSse")
    public SseEmitter v() {
        SseEmitter emitter = new SseEmitter();
        WebClient webclient = WebClient.create();
        new Thread(() -> {
            try {
                webclient.get()
                        .uri("http://127.0.0.1:8763/sse3/stream")
                        .retrieve()
                        .bodyToFlux(byte[].class)
                        .subscribe(bytes -> {
                                    log.info("length={}", bytes.length);
                                    try {
                                        emitter.send(bytes, MediaType.APPLICATION_OCTET_STREAM);
                                    } catch (Exception e) {
                                        log.error("", e);
                                        emitter.completeWithError(e);
                                    }
                                }, e -> emitter.completeWithError(e)
                                , emitter::complete);
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }

    final ExecutorService executorService = Executors.newCachedThreadPool();

    @GetMapping("/stream")
    public ResponseBodyEmitter stream() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        executorService.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    String data = "数据块" + i + ",";
                    data = data.repeat(10);
                    byte[] bytes = data.getBytes();
                    bytes = Base64.getEncoder().encode(bytes);
                    emitter.send(bytes);
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (Exception e) {
                    log.error("", e);
                    emitter.completeWithError(e);
                }
            }
            emitter.complete();
        });
        return emitter;
    }
}

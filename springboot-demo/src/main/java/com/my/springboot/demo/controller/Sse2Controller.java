package com.my.springboot.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/sse2")
public class Sse2Controller {
    public WebClient sseWebClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().responseTimeout(Duration.ofSeconds(60))))
                .build();
    }

    @GetMapping(value = "/forward", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> forwardSseStream() {
        WebClient sseWebClient = sseWebClient();
        // 设置响应头，确保下游客户端识别为 SSE
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_EVENT_STREAM);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.set("Connection", "keep-alive");

        String upstreamUrl = "http://127.0.0.1:8763/sse/5";
        log.info("Forwarding SSE stream from {}", upstreamUrl);
        StreamingResponseBody stream = out -> {
            // 异步获取上游 SSE 流
            sseWebClient.get()
                    .uri(upstreamUrl)
                    .retrieve()
                    .bodyToFlux(ByteBuffer.class) // 以 ByteBuffer 流形式接收
                    .doOnNext(buffer -> {
                        try {
                            // 将 ByteBuffer 转为 byte[] 并写入响应输出流
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            out.write(bytes);
                            out.flush(); // 立即刷新，保证实时性
                        } catch (IOException e) {
                            // 客户端断开时捕获异常，避免服务端报错
                            log.warn("Client disconnected during SSE forwarding", e);
                        }
                    })
                    .doOnError(e -> {
                        log.error("Error forwarding SSE stream", e);
                        try {
                            out.write(("data: {" + "\"error\":\"" + e.getMessage() + "\"}\n\n").getBytes(StandardCharsets.UTF_8));
                            out.flush();
                        } catch (IOException ignored) {
                        }
                    })
                    .doOnComplete(() -> {
                        try {
                            // SSE 结束标记
                            out.write("event: close\ndata: Stream ended\n\n".getBytes(StandardCharsets.UTF_8));
                            out.flush();
                        } catch (IOException ignored) {
                        }
                    })
                    .subscribe();
        };

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream);
    }
}

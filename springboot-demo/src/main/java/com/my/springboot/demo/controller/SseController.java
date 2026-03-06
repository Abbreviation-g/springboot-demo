package com.my.springboot.demo.controller;

import com.my.springboot.demo.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping("/sse")
@Controller
public class SseController {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @GetMapping("/1")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter();
        executorService.execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send("Message " + i, MediaType.TEXT_PLAIN);
                    TimeUnit.SECONDS.sleep(1);
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @GetMapping("/2")
    public SseEmitter handleSse2() {
        SseEmitter emitter = new SseEmitter();
        final OkHttpClient okHttpClient = createClient();

        EventSource.Factory factory = EventSources.createFactory(okHttpClient);
        Request httpRequest = new Request.Builder()
//                .header("Accept", "audio/pcm")
                .url("http://127.0.0.1:8763/sse/1")
                .get()
                .build();
        factory.newEventSource(httpRequest, new TTSEventListener(emitter));
        return emitter;
    }

    @GetMapping("/3")
    public SseEmitter handleSse3() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (i == 9) {
                for (int j = 0; j < 9; j++) {
                    sb.append(j);
                }
            } else {
                for (int j = 0; j < 10; j++) {
                    sb.append(j);
                }
            }
        }

        SseEmitter emitter = new SseEmitter();
        executorService.execute(() -> {
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(sb.toString().getBytes());
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/output.wav");
            if (inputStream == null) {
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "inputStream is null");
            }
            try {
                byte[] buffer = new byte[10];
                int length = 0;
                while ((length = inputStream.read(buffer)) != -1) {
                    if (length == buffer.length) {
                        emitter.send(buffer, MediaType.APPLICATION_OCTET_STREAM);
                    } else {
                        byte[] data = new byte[length];
                        System.arraycopy(buffer, 0, data, 0, length);
                        emitter.send(data, MediaType.APPLICATION_OCTET_STREAM);
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (IOException | InterruptedException e) {
                log.error("inputStream error", e);
            } finally {
                emitter.complete();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("inputStream close error", e);
                }
            }
        });
        return emitter;
    }

    @GetMapping("/4")
    public SseEmitter handleSse4() {
        SseEmitter emitter = new SseEmitter();
        final OkHttpClient okHttpClient = createClient();

        EventSource.Factory factory = EventSources.createFactory(okHttpClient);
        Request httpRequest = new Request.Builder()
//                .header("Accept", "audio/pcm")
                .url("http://127.0.0.1:8763/sse/3")
                .get()
                .build();
        factory.newEventSource(httpRequest, new TTSEventListener(emitter));
        return emitter;
    }

    @GetMapping("/5")
    public SseEmitter handleSse5() {
        MediaType pcmMediaType =  MediaType.valueOf("audio/pcm");
        SseEmitter emitter = new SseEmitter();
        executorService.execute(() -> {
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(sb.toString().getBytes());
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/output.wav");
            if (inputStream == null) {
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "inputStream is null");
            }
            try {
                byte[] buffer = new byte[10];
                int length = 0;
                while ((length = inputStream.read(buffer)) != -1) {
                    if (length == buffer.length) {
                        emitter.send(buffer, pcmMediaType);
                    } else {
                        byte[] data = new byte[length];
                        System.arraycopy(buffer, 0, data, 0, length);
                        emitter.send(data, pcmMediaType);
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (IOException | InterruptedException e) {
                log.error("inputStream error", e);
            } finally {
                emitter.complete();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("inputStream close error", e);
                }
            }
        });
        return emitter;
    }

    @GetMapping("/6")
    public SseEmitter handleSse6() {
        SseEmitter emitter = new SseEmitter();
        final OkHttpClient okHttpClient = createClient();

        EventSource.Factory factory = EventSources.createFactory(okHttpClient);
        Request httpRequest = new Request.Builder()
//                .header("Accept", "audio/pcm")
                .url("http://127.0.0.1:8763/sse/5")
                .get()
                .build();
        factory.newEventSource(httpRequest, new TTSEventListener(emitter));
        return emitter;
    }

    public static OkHttpClient createClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Response originalResponse = chain.proceed(chain.request());
                    String contentTypeHeader = originalResponse.header("Content-Type");
                    if (contentTypeHeader != null && contentTypeHeader.startsWith("audio/pcm")) {
                        // 创建一个新的 Response.Builder
                        Response.Builder responseBuilder = originalResponse.newBuilder();
                        // 将 Content-Type 修改为通用的二进制流类型
                        responseBuilder.header("Content-Type", "application/octet-stream");
                        return responseBuilder.build();
                    }
                    return originalResponse;
                })
                .connectionPool(new ConnectionPool(30, 100, TimeUnit.SECONDS))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
        client.dispatcher().setMaxRequestsPerHost(150);
        client.dispatcher().setMaxRequests(150);
        return client;
    }


    private static class TTSEventListener extends EventSourceListener {
        private SseEmitter emitter;

        TTSEventListener(SseEmitter emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onOpen(EventSource eventSource, Response response) {
            log.info("onOpen");
            try {
                emitter.send("onOpen");
            } catch (IOException e) {
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "onOpen error");
            }
        }

        @Override
        public void onEvent(EventSource eventSource, String id, String type, String data) {
            log.info("onEvent, data={}", data);
            try {
                emitter.send(data);
            } catch (IOException e) {
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "onEvent error");
            }
        }

        @Override
        public void onClosed(EventSource eventSource) {
            log.info("onClosed");
            try {
                emitter.send("onClosed");
            } catch (IOException e) {
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "onClosed error");
            } finally {
                emitter.complete();
            }
        }

        @Override
        public void onFailure(EventSource eventSource, Throwable t, Response response) {
            log.error("onFailure", t);
            try {
                emitter.send("onFailure");
            } catch (IOException e) {
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "onFailure error");
            } finally {
                emitter.completeWithError(t);
            }
        }
    }
}

//package com.my.springboot.demo.websocket;
//
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.handshake.ServerHandshake;
//import org.json.JSONObject;
//
//import java.net.URI;
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.UUID;
//import java.util.concurrent.CountDownLatch;
//
//public class QwenAsrWsDemo {
//    public static void main(String[] args) throws Exception {
//        String requestId = UUID.randomUUID().toString().replace("-", "");
//        String url = "ws://172.16.30.26:8125/ws/asr?request_id=" + requestId;
//        CountDownLatch latch = new CountDownLatch(1);
//        WebSocketClient client = new WebSocketClient(new URI(url)) {
//            @Override
//            public void onOpen(ServerHandshake handshake) {
//                System.out.println("WebSocket 已连接");
//            }
//
//            @Override
//            public void onMessage(String message) {
//                JSONObject msg = new JSONObject(message);
//                String event = msg.getString("event");
//                switch (event) {
//                    case "ready":
//                        System.out.println("会话就绪，session_id: " + msg.getString("session_id"));
//                        // 可选：设置音频格式（默认已为 pcm）
//                        send(new JSONObject().put("event", "config").put("format", "pcm").toString());
//                        // 开始推送音频数据
//                        sendAudioAndFinish(this);
//                        break;
//                    case "result":
//                        System.out.println("实时识别: " + msg.optString("text"));
//                        break;
//                    case "final":
//                        System.out.println("最终结果: " + msg.optString("text"));
//                        latch.countDown();
//                        close();
//                        break;
//                    case "error":
//                        System.err.println("错误: " + msg.optString("message"));
//                        latch.countDown();
//                        close();
//                        break;
//                }
//            }
//
//            @Override
//            public void onClose(int code, String reason, boolean remote) {
//                System.out.println("连接已关闭: " + reason);
//                latch.countDown();
//            }
//
//            @Override
//            public void onError(Exception ex) {
//                System.err.println("WebSocket 错误: " + ex.getMessage());
//                latch.countDown();
//            }
//        };
//
//        client.connect();
//        latch.await(); // 等待识别完成
//    }
//
//    /**
//     * 读取 PCM Float32 文件并分帧推送，推送完毕后发送 finish 事件。
//     * PCM 格式要求：单声道、16kHz、IEEE 754 Float32、Little-Endian。
//     */
//    private static void sendAudioAndFinish(WebSocketClient ws) {
//        new Thread(() -> {
//            try {
//                // 读取原始 PCM float32 文件
//                byte[] pcmBytes = Files.readAllBytes(Paths.get("audio.pcm"));// 按 3200 字节（400ms@16kHz float32）分帧推送int chunkSize = 3200; // 800 samples * 4 bytesfor (int offset = 0; offset < pcmBytes.length; offset += chunkSize) {int len = Math.min(chunkSize, pcmBytes.length - offset);ByteBuffer buf = ByteBuffer.allocate(len).order(ByteOrder.LITTLE_ENDIAN);
//                buf.put(pcmBytes, offset, len);
//                ws.send(buf.array());
//                Thread.sleep(100); // 模拟实时采集间隔}// 通知服务端结束，获取最终结果
//                ws.send(new JSONObject().put("event", "finish").toString());
//            } catch (Exception e) {
//                System.err.println("音频推送失败: " + e.getMessage());
//            }
//        }).start();
//    }
//}
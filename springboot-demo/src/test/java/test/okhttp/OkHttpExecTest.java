package test.okhttp;

import io.kubernetes.client.Exec;
import io.kubernetes.client.openapi.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.WebSocketStreamHandler;
import io.kubernetes.client.util.WebSockets;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpExecTest {
    private ApiClient client;
    private OkHttpClient httpClient;
    private String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InFtWmFudFZvVzdvZTBrVlhaZWJpbWxXQ0I4ZWpVOWVNRGtjYkRzVFVrZFUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImFkbWluLXNlcnZpY2UtYWNjb3VudC10b2tlbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbi1zZXJ2aWNlLWFjY291bnQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJmMTMzZDRlNi0zMDliLTQ0ZTMtYTQzYi0wN2RhZTE4ODVkMjciLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDphZG1pbi1zZXJ2aWNlLWFjY291bnQifQ.wT4RDqqLM7DA51Ood4m95R2Z6plPYA46HhSZMrd2q5d6snkG-0g5RBb_fkGuhWvBAT90_EhbM-JT2DHYx1C0lGTF02sKxRe0OnhsJlSK_W2eiVBSE3KEANc_VbZzTuAfWQeRB7rBHg2ldMl1a1hR4dhHj_koYN9-39v0hRQ-LGuhGgCHjXVIvx-Kr8lgWLf_7zxtlnKv-jjZzVgpDc6QCPEiImyp8zhi4StgJNc6ZUsO-5G1w99VnClYJebmxkBXAwJBvi-qX5rJtekUhaDcLr4BneSFTMz5Aa-l7w1S17shP93aM2hqoZUdGkzsm5z_ddrmi9AgRJxCiE-CExVBKg";
    private String baseUrl = "https://172.16.31.35:6443";
    private String namespace = "namespace-0327";
    private String podName = "nginx-pod";
    private String container = "nginx-container";
    ;

    private void turnOffSsl() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustManagers;
        HostnameVerifier hostnameVerifier;
        trustManagers =
                new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(
                                    java.security.cert.X509Certificate[] chain, String authType)
                                    throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(
                                    java.security.cert.X509Certificate[] chain, String authType)
                                    throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };
        hostnameVerifier =
                new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, new SecureRandom());

        List<Protocol> protocols = Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1);
        Duration pingInterval = Duration.ofMinutes(1);
        Duration readTimeout = Duration.ofMillis(1000);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        httpClient = builder
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
                .hostnameVerifier(hostnameVerifier)
                .protocols(protocols)
                .pingInterval(pingInterval)
                .readTimeout(readTimeout)
                .addNetworkInterceptor(getProgressInterceptor())
                .build();
    }

    private Interceptor getProgressInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                final Request request = chain.request();
                final Response originalResponse = chain.proceed(request);
                if (request.tag() instanceof ApiCallback) {
                    final ApiCallback callback = (ApiCallback) request.tag();
                    return originalResponse
                            .newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), callback))
                            .build();
                }
                return originalResponse;
            }
        };
    }

    @Before
    public void setup() throws NoSuchAlgorithmException, KeyManagementException {


//        this.client = new ClientBuilder().setBasePath(baseUrl).setAuthentication(new AccessTokenAuthentication(token)).build();
//        client.setVerifyingSsl(false);
//        Configuration.setDefaultApiClient(client);

        turnOffSsl();
    }

    public static final String V4_STREAM_PROTOCOL = "v4.channel.k8s.io";
    public static final String STREAM_PROTOCOL_HEADER = "Sec-WebSocket-Protocol";
    public static final String SPDY_3_1 = "SPDY/3.1";
    public static final String CONNECTION = "Connection";
    public static final String UPGRADE = "Upgrade";

    @Test
    public void test() throws Exception {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(STREAM_PROTOCOL_HEADER, V4_STREAM_PROTOCOL);
        headers.put(WebSockets.CONNECTION, WebSockets.UPGRADE);
        headers.put(WebSockets.UPGRADE, SPDY_3_1);
        String[] localVarAuthNames = new String[]{"BearerToken"};

        String path = "/api/v1/namespaces/namespace-0327/pods/nginx-pod/exec?stdin=true&stdout=true&stderr=true&tty=false&container=nginx-container&command=ls";
        String method = "GET";
        List<Pair> queryParams = new ArrayList<Pair>();
        WebSockets.SocketListener listener = new WebSockets.SocketListener() {
            @Override
            public void open(String protocol, WebSocket socket) {
                System.out.println("WebSocket 已连接");
                System.out.println("protocol:" + protocol);
                socket.send("ls");
            }

            @Override
            public void bytesMessage(InputStream in) {
                try {
                    byte[] bytes = new byte[1024];
                    while (in.read(bytes) != -1) {
                        System.out.println(new String(bytes));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void textMessage(Reader in) {
                try {
                    BufferedReader reader = new BufferedReader(in);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void close() {
                System.out.println("WebSocket 已关闭");
            }
        };
        Request request =
                client.buildRequest(
                        path,
                        method,
                        queryParams,
                        new ArrayList<Pair>(),
                        null,
                        headers,
                        new HashMap<String, String>(),
                        new HashMap<String, Object>(),
                        localVarAuthNames,
                        null);
//        ExecProcess exec = new ExecProcess(client);
//        WebSocketStreamHandler handler = exec.getHandler();
        httpClient.newWebSocket(request, new WebSockets.Listener(listener));
    }

    public static class ExecProcess extends Process {
        private final WebSocketStreamHandler streamHandler;
        private int statusCode = -1;
        private boolean isAlive = true;
        private final Map<Integer, InputStream> input = new HashMap<>();
        private final CountDownLatch latch = new CountDownLatch(1);

        public ExecProcess(final ApiClient apiClient) throws IOException {
            this.streamHandler =
                    new WebSocketStreamHandler() {
                        @Override
                        protected void handleMessage(int stream, InputStream inStream) throws IOException {
                            if (stream == 3) {
                                int exitCode = 0;
                                if (exitCode >= 0) {
                                    // notify of process completion
                                    synchronized (ExecProcess.this) {
                                        statusCode = exitCode;
                                        isAlive = false;
                                    }
                                }
                                inStream.close();
                                // Stream ID of `3` delivers the status of exec connection from
                                // kubelet,
                                // closing the connection upon 0 exit-code.
                                this.close();
                                ExecProcess.this.latch.countDown();
                            } else super.handleMessage(stream, inStream);
                        }

                        @Override
                        public void failure(Throwable ex) {
                            super.failure(ex);
                            // TODO, it's possible we should suppress this error message, but
                            // currently there's
                            // no good place to surface the message, and without it, this will be
                            // really hard to
                            // debug.
                            ex.printStackTrace();
                            synchronized (ExecProcess.this) {
                                // Try for a pretty unique error code, so if someone searches
                                // they'll find this
                                // code.
                                statusCode = -1975219;
                                isAlive = false;
                                ExecProcess.this.latch.countDown();
                            }
                        }

                        @Override
                        public void close() {
                            // notify of process completion
                            synchronized (ExecProcess.this) {
                                if (isAlive) {
                                    isAlive = false;
                                    ExecProcess.this.latch.countDown();
                                }
                            }

                            super.close();
                        }
                    };
        }

        // Protected to facilitate unit testing.
        protected WebSocketStreamHandler getHandler() {
            return streamHandler;
        }

        @Override
        public OutputStream getOutputStream() {
            return streamHandler.getOutputStream(0);
        }

        @Override
        public InputStream getInputStream() {
            return getInputStream(1);
        }

        @Override
        public InputStream getErrorStream() {
            return getInputStream(2);
        }

        public InputStream getConnectionErrorStream() {
            return getInputStream(3);
        }

        public OutputStream getResizeStream() {
            return streamHandler.getOutputStream(4);
        }

        private synchronized InputStream getInputStream(int stream) {
            if (!input.containsKey(stream)) {
                input.put(stream, streamHandler.getInputStream(stream));
            }
            return input.get(stream);
        }

        @Override
        public int waitFor() throws InterruptedException {
            this.latch.await();
            return statusCode;
        }

        @Override
        public boolean waitFor(long timeout, TimeUnit unit) throws InterruptedException {
            this.latch.await(timeout, unit);
            return !isAlive();
        }

        @Override
        public synchronized int exitValue() {
            if (isAlive) throw new IllegalThreadStateException();
            return statusCode;
        }

        @Override
        public synchronized boolean isAlive() {
            return isAlive;
        }

        @Override
        public void destroy() {
            streamHandler.close();
            for (InputStream in : input.values()) {
                try {
                    in.close();
                } catch (IOException ex) {
                    log.error("Error on close", ex);
                }
            }
        }
    }

    @Test
    public void test2() throws InterruptedException {
        Request request = new Request.Builder()
                .url("https://172.16.31.35:6443/api/v1/namespaces/namespace-0327/pods/nginx-pod/exec?stdin=true&stdout=true&stderr=true&tty=false&container=nginx-container&command=ls")
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader(STREAM_PROTOCOL_HEADER, V4_STREAM_PROTOCOL)
                .addHeader(WebSockets.CONNECTION, WebSockets.UPGRADE)
                .addHeader(WebSockets.UPGRADE, SPDY_3_1)
                .addHeader("User-Agent", "Kubernetes Java Client/15.0.0-SNAPSHOT2")
                .build();
        httpClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                System.out.println("onOpen");
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                System.out.println("onMessage");
                System.out.println(text);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                System.out.println("onMessage. ByteString");
                System.out.println(new String(bytes.toByteArray()));
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                System.out.println("onClosed");
                System.out.println(code);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                System.out.println("onClosing");
                System.out.println(code);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                System.out.println("onFailure");
                t.printStackTrace();
            }
        });
        Thread.sleep(1000 * 3);
    }


    @Test
    public void testBash() throws ApiException, InterruptedException {
        Request request = new Request.Builder()
                .url("https://172.16.31.35:6443/api/v1/namespaces/namespace-0327/pods/nginx-pod/exec" +
                        "?stdin=true" +
                        "&stdout=true" +
                        "&stderr=true" +
                        "&tty=true" +
                        "&container=nginx-container" +
                        "&command=bash")
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader(STREAM_PROTOCOL_HEADER, V4_STREAM_PROTOCOL)
                .addHeader(WebSockets.CONNECTION, WebSockets.UPGRADE)
                .addHeader(WebSockets.UPGRADE, SPDY_3_1)
                .addHeader("User-Agent", "Kubernetes Java Client/15.0.0-SNAPSHOT2")
                .build();
        httpClient.newWebSocket(request, new WebSocketListener() {
            Timer timer;
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                System.out.println("onOpen");
                System.out.println(response);
//                webSocket.send("[\"{\\\"Op\\\":\\\"bind\\\",\\\"SessionID\\\":\\\"42f88dd71b684a9215f2c88f5166637e\\\"}\"]");
//                webSocket.send("[\"{\\\"Op\\\":\\\"stdin\\\",\\\"Data\\\":\\\"ls\\\",\\\"Cols\\\":229,\\\"Rows\\\":24}\"]");
//                webSocket.send("{\"Op\":\"stdin\",\"Data\":\"ls\"}");
//                webSocket.send("ls");
//                webSocket.send("[\"{\\\"Op\\\":\\\"bind\\\",\\\"SessionID\\\":\\\"42f88dd71b684a9215f2c88f5166637e\\\"}\"]");

            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                System.out.println("onMessage(@NotNull WebSocket webSocket, @NotNull String text)");
                System.out.println(text);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                System.out.println("onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes)");
                System.out.println(new String(bytes.toByteArray()));

                String line = new String(bytes.toByteArray());
                System.out.println(line);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        webSocket.send(ByteString.of("{\"Op\":\"stdout\",\"Data\":\"ls\"}".getBytes()));
                        webSocket.send("[\"{\\\"Op\\\":\\\"stdout\\\",\\\"Data\\\":\\\"ls\\\",\\\"Cols\\\":229,\\\"Rows\\\":24}\"]");
                        webSocket.send("\r\n");
                    }
                }, 1000, 1000);
//                webSocket.send("ls\n");
//                webSocket.send("ls");
            }

            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                System.out.println("onClosed");
                System.out.println(code);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                System.out.println("onClosing");
                System.out.println(code);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                System.out.println("onFailure");
                t.printStackTrace();
            }
        });
        Thread.sleep(1000 * 60);
    }
}

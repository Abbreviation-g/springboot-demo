package test.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static test.okhttp.WebSocketConstants.*;

@Slf4j
public class ExecProcess extends Process {
    private final WebSocketStreamHandler streamHandler;
    private int statusCode = -1;
    private boolean isAlive = true;
    private final Map<Integer, InputStream> input = new HashMap<>();
    private final CountDownLatch latch = new CountDownLatch(1);

    public ExecProcess() throws IOException {
        this.streamHandler =
                new WebSocketStreamHandler() {
                    @Override
                    protected void handleMessage(int stream, InputStream inStream) throws IOException {
                        if (stream == 3) {
                            int exitCode = parseExitCode(inStream);
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
    public WebSocketStreamHandler getHandler() {
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

    static int parseExitCode(InputStream inputStream) {
        try {
            String body;
            try (final Reader reader = new InputStreamReader(inputStream)) {
                body = Streams.toString(reader);
            }
            JSONObject json = JSON.parseObject(body);
            // {"metadata":{},"status":"Success"}
            String status = json.getString("status");
            if (V1STATUS_SUCCESS.equals(status)) {
                return 0;
            }
            String reason = json.getString("reason");
            Integer code = json.getInteger("code");

            if (V1STATUS_REASON_NONZEROEXITCODE.equals(reason)) {
                JSONObject details = json.getJSONObject("details");
                if (details != null) {
                    JSONArray causes = details.getJSONArray("causes");
                    if (causes != null) {
                        for (int i = 0; i < causes.size(); i++) {
                            JSONObject cause = causes.getJSONObject(i);
                            String causeReason = cause.getString("reason");
                            String causeMessage = cause.getString("message");
                            if (V1STATUS_CAUSE_REASON_EXITCODE.equals(causeReason)) {
                                try {
                                    return Integer.parseInt(causeMessage);
                                } catch (NumberFormatException nfe) {
                                    log.error("Error parsing exit code from status channel response", nfe);
                                }
                            }
                        }
                    }
                }
            }
            log.error("Error parsing status channel response: {}", body);
            return code;
        } catch (Throwable t) {
            log.error("Error parsing exit code from status channel response", t);
        }

        // Unable to parse the exit code from the content
        return -1;
    }
}
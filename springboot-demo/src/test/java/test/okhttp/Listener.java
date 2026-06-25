package test.okhttp;


import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

public class Listener extends WebSocketListener {
    private SocketListener listener;

    public Listener(SocketListener listener) {
        this.listener = listener;
    }

    @Override
    public void onOpen(final WebSocket webSocket, Response response) {
        String protocol = response.header(WebSocketConstants.STREAM_PROTOCOL_HEADER, "missing");
        listener.open(protocol, webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        listener.textMessage(new StringReader(text));
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        listener.bytesMessage(new ByteArrayInputStream(bytes.toByteArray()));
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        listener.close();
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        listener.failure(t);
        listener.close();
    }
}
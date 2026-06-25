package test.okhttp;

import okhttp3.WebSocket;

import java.io.InputStream;
import java.io.Reader;

public interface SocketListener {
    /** Called when the socket is opened */
    void open(String protocol, WebSocket socket);

    /**
     * Called when a binary media type message is received
     *
     * @param in The input stream containing the binary data
     */
    void bytesMessage(InputStream in);

    /**
     * Called when a text media type message is received
     *
     * @param in The character stream containing the message
     */
    void textMessage(Reader in);

    /**
     * Called when there has been a failure
     *
     * @param t the exception associated with the failure.
     */
    void failure(Throwable t);

    /** Called when the stream is closed. */
    void close();
  }
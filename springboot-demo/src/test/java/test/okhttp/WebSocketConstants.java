package test.okhttp;

public class WebSocketConstants {
    public static final String V4_STREAM_PROTOCOL = "v4.channel.k8s.io";
    public static final String STREAM_PROTOCOL_HEADER = "Sec-WebSocket-Protocol";
    public static final String SPDY_3_1 = "SPDY/3.1";
    public static final String CONNECTION = "Connection";
    public static final String UPGRADE = "Upgrade";

    // V1Status values
    public static final String V1STATUS_SUCCESS = "Success";
    public static final String V1STATUS_FAILURE = "Failure";

    // V1Status reason values
    public static final String V1STATUS_REASON_NONZEROEXITCODE = "NonZeroExitCode";

    // V1Status cause reason values
    public static final String V1STATUS_CAUSE_REASON_EXITCODE = "ExitCode";
}

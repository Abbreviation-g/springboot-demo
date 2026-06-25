package test.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static test.okhttp.WebSocketConstants.*;

@Slf4j
public class OkHttpExecTest2 {
    private String namespace = "namespace-0327";
    private String podName = "nginx-pod";
    private String container = "nginx-container";
    private String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InFtWmFudFZvVzdvZTBrVlhaZWJpbWxXQ0I4ZWpVOWVNRGtjYkRzVFVrZFUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImFkbWluLXNlcnZpY2UtYWNjb3VudC10b2tlbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbi1zZXJ2aWNlLWFjY291bnQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJmMTMzZDRlNi0zMDliLTQ0ZTMtYTQzYi0wN2RhZTE4ODVkMjciLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDphZG1pbi1zZXJ2aWNlLWFjY291bnQifQ.wT4RDqqLM7DA51Ood4m95R2Z6plPYA46HhSZMrd2q5d6snkG-0g5RBb_fkGuhWvBAT90_EhbM-JT2DHYx1C0lGTF02sKxRe0OnhsJlSK_W2eiVBSE3KEANc_VbZzTuAfWQeRB7rBHg2ldMl1a1hR4dhHj_koYN9-39v0hRQ-LGuhGgCHjXVIvx-Kr8lgWLf_7zxtlnKv-jjZzVgpDc6QCPEiImyp8zhi4StgJNc6ZUsO-5G1w99VnClYJebmxkBXAwJBvi-qX5rJtekUhaDcLr4BneSFTMz5Aa-l7w1S17shP93aM2hqoZUdGkzsm5z_ddrmi9AgRJxCiE-CExVBKg";
    private String baseUrl = "https://172.16.31.35:6443";
    ;

    @Before
    public void setup() throws NoSuchAlgorithmException, KeyManagementException {
        turnOffSsl();
    }

    private OkHttpClient httpClient;

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
                .build();
    }

    @Test
    public void testExec2() throws Exception {
        String path = "/api/v1/namespaces/{namespace}/pods/{pod}/exec";
        path = path.replace("{namespace}", namespace);
        path = path.replace("{pod}", podName);
        path = baseUrl + path;
        path = path + "?stdin=" + true;
        path = path + "&stdout=" + true;
        path = path + "&stderr=" + true;
        path = path + "&tty=" + false;
        path = path + "&container=" + container;
        path = path + "&command=" + "xx";

        Request request = new Request.Builder()
                .url(path)
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader(STREAM_PROTOCOL_HEADER, V4_STREAM_PROTOCOL)
                .addHeader(CONNECTION, UPGRADE)
                .addHeader(UPGRADE, SPDY_3_1)
                .addHeader("User-Agent", "Kubernetes Java Client/15.0.0-SNAPSHOT2")
                .build();
        ExecProcess exec = new ExecProcess();
        WebSocketStreamHandler handler = exec.getHandler();
        httpClient.newWebSocket(request, new Listener(handler));

        exec.getInputStream().transferTo(System.out);
        exec.waitFor();
        System.out.println(exec.exitValue());
    }
}

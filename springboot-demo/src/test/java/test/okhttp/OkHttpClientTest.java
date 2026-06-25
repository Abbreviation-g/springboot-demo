package test.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpClientTest {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = httpClient();
        String token = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InFtWmFudFZvVzdvZTBrVlhaZWJpbWxXQ0I4ZWpVOWVNRGtjYkRzVFVrZFUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImFkbWluLXNlcnZpY2UtYWNjb3VudC10b2tlbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbi1zZXJ2aWNlLWFjY291bnQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJmMTMzZDRlNi0zMDliLTQ0ZTMtYTQzYi0wN2RhZTE4ODVkMjciLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDphZG1pbi1zZXJ2aWNlLWFjY291bnQifQ.wT4RDqqLM7DA51Ood4m95R2Z6plPYA46HhSZMrd2q5d6snkG-0g5RBb_fkGuhWvBAT90_EhbM-JT2DHYx1C0lGTF02sKxRe0OnhsJlSK_W2eiVBSE3KEANc_VbZzTuAfWQeRB7rBHg2ldMl1a1hR4dhHj_koYN9-39v0hRQ-LGuhGgCHjXVIvx-Kr8lgWLf_7zxtlnKv-jjZzVgpDc6QCPEiImyp8zhi4StgJNc6ZUsO-5G1w99VnClYJebmxkBXAwJBvi-qX5rJtekUhaDcLr4BneSFTMz5Aa-l7w1S17shP93aM2hqoZUdGkzsm5z_ddrmi9AgRJxCiE-CExVBKg";
        String url = "https://172.16.31.35:6443/api/v1/namespaces/namespace-0327/pods/nginx-pod/exec?container=nginx-container&stdout=true&stderr=true&command=ls&command=%2F";
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer "+token)
                .header("Upgrade", "SPDY/3.1")
                .header("Connection", "Upgrade")
                .get()
                .build();
        okHttpClient.newCall(new okhttp3.Request.Builder().url(url).get().build()).execute();
    }
    public static OkHttpClient httpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(30, 100, TimeUnit.SECONDS))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        okHttpClient.dispatcher().setMaxRequestsPerHost(150);
        okHttpClient.dispatcher().setMaxRequests(150);
        return okHttpClient;
    }
}

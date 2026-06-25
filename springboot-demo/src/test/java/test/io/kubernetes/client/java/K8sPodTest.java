package test.io.kubernetes.client.java;

import io.kubernetes.client.Attach;
import io.kubernetes.client.Exec;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.WebSocketStreamHandler;
import io.kubernetes.client.util.WebSockets;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;
import okhttp3.WebSocket;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.function.Consumer;

import static org.mockito.Mockito.mock;

public class K8sPodTest {
    private ApiClient client;
    private String namespace = "namespace-0327";
    private String podName = "nginx-pod";
    private String container = "nginx-container";
    ;

    @Before
    public void setup() {
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InFtWmFudFZvVzdvZTBrVlhaZWJpbWxXQ0I4ZWpVOWVNRGtjYkRzVFVrZFUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImFkbWluLXNlcnZpY2UtYWNjb3VudC10b2tlbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbi1zZXJ2aWNlLWFjY291bnQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJmMTMzZDRlNi0zMDliLTQ0ZTMtYTQzYi0wN2RhZTE4ODVkMjciLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDphZG1pbi1zZXJ2aWNlLWFjY291bnQifQ.wT4RDqqLM7DA51Ood4m95R2Z6plPYA46HhSZMrd2q5d6snkG-0g5RBb_fkGuhWvBAT90_EhbM-JT2DHYx1C0lGTF02sKxRe0OnhsJlSK_W2eiVBSE3KEANc_VbZzTuAfWQeRB7rBHg2ldMl1a1hR4dhHj_koYN9-39v0hRQ-LGuhGgCHjXVIvx-Kr8lgWLf_7zxtlnKv-jjZzVgpDc6QCPEiImyp8zhi4StgJNc6ZUsO-5G1w99VnClYJebmxkBXAwJBvi-qX5rJtekUhaDcLr4BneSFTMz5Aa-l7w1S17shP93aM2hqoZUdGkzsm5z_ddrmi9AgRJxCiE-CExVBKg";
        String baseUrl = "https://172.16.31.35:6443";

        this.client = new ClientBuilder().setBasePath(baseUrl).setAuthentication(new AccessTokenAuthentication(token)).build();
        client.setVerifyingSsl(false);
        Configuration.setDefaultApiClient(client);
    }

    @Test
    public void testListAllPods() throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
    }

    @Test
    public void testListPodWithNamespace() throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listNamespacedPod(namespace, null, null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
    }

    @Test
    public void testGetPod() throws ApiException {
        CoreV1Api api = new CoreV1Api();
        V1Pod readPod = api.readNamespacedPod(podName, namespace, "pretty");
        System.out.println(readPod);
    }

    @Test
    public void testExecInPod() throws IOException, ApiException, InterruptedException {
        Exec exec = new Exec(client);
        String[] cmd = {"ls"};

        Process p = exec.exec(namespace, podName, cmd, container, true, false);
//        p.getInputStream().transferTo(System.out);
//        p.waitFor();
//        System.out.println(p.exitValue());
        Thread.sleep(1000*3);
    }

    public static class MyExecProcess extends Exec.ExecProcess {

        public MyExecProcess(ApiClient apiClient) throws IOException {
            super(apiClient);
        }

        @Override
        public WebSocketStreamHandler getHandler() {
            return super.getHandler();
        }
    }

    @Test
    public void testExecInPod2() throws IOException, ApiException, InterruptedException {
        String path = "/api/v1/namespaces/namespace-0327/pods/nginx-pod/exec?stdin=true&stdout=true&stderr=true&tty=false&container=nginx-container&command=ls";

//        MyExecProcess exec = new MyExecProcess(client);
//        WebSocketStreamHandler handler = exec.getHandler();
//        WebSockets.stream(path, "GET", client, handler);
        WebSockets.stream(path, "GET", client, new WebSockets.SocketListener() {
            @Override
            public void open(String protocol, WebSocket socket) {
                System.out.println("WebSocket 已连接");
            }

            @Override
            public void bytesMessage(InputStream in) {
                System.out.println("bytesMessage");
                try {
                    in.transferTo(System.out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void textMessage(Reader in) {
                System.out.println("textMessage");
            }

            @Override
            public void failure(Throwable t) {
                System.out.println("failure");
            }

            @Override
            public void close() {
                System.out.println("close");
            }
        });
        Thread.sleep(1000*3);
    }

    @Test
    public void testOpenWebSocket() throws IOException, ApiException, InterruptedException {
        String path = "/api/v1/namespaces/{namespace}/pods/{pod}/attach?stderr=true&stdout=true&container=${container}";
        path = path.replace("{namespace}", namespace);
        path = path.replace("{pod}", podName);
        path = path.replace("${container}", container);
        WebSockets.stream(
                path,
                "GET",
                client,
                new WebSockets.SocketListener() {
                    private volatile WebSocket socket;

                    @Override
                    public void open(String protocol, WebSocket socket) {
                        this.socket = socket;
                        System.out.println("WebSocket 已连接");
                        socket.send("[\"{\\\"Op\\\":\\\"bind\\\",\\\"SessionID\\\":\\\"42f88dd71b684a9215f2c88f5166637e\\\"}\"]");
                        socket.send("[\"{\\\"Op\\\":\\\"resize\\\",\\\"Cols\\\":229,\\\"Rows\\\":24}\"]");
                        socket.send("[\"{\\\"Op\\\":\\\"stdin\\\",\\\"Data\\\":\\\"ls\\\",\\\"Cols\\\":229,\\\"Rows\\\":24}\"]");
                    }

                    @Override
                    public void close() {
                        System.out.println("WebSocket 已关闭");
                    }

                    @Override
                    public void bytesMessage(InputStream is) {
                        System.out.println("bytesMessage");
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                                System.out.println(line);
                                socket.send("cd /");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        socket.send("exit");
                    }

                    @Override
                    public void failure(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void textMessage(Reader in) {
                        System.out.println("textMessage");
                        try {
                            BufferedReader reader = new BufferedReader(in);
                            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                                System.out.println(line);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        Thread.sleep(1000*20);
    }

    @Test
    public void testAttach() throws IOException, ApiException, InterruptedException {
        Attach attach = new Attach(client);
        Attach.AttachResult res1 = attach.attach(namespace, podName, container, true, true);
//        inputStream1.read();

        res1.getStandardInputStream().write("ls".getBytes());
        Thread.sleep(1000);

        InputStream inputStream1 = res1.getStandardOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            System.out.println(line);
        }

        inputStream1.close();
        res1.close();

//        Attach.AttachResult res2 = attach.attach(namespace, podName, true);
//        InputStream inputStream2 = res2.getStandardOutputStream();
//        inputStream2.read();
//        inputStream2.close();
//        res2.close();
//
//        Attach.AttachResult res3 =
//                attach
//                        .newConnectionBuilder(namespace, podName)
//                        .setContainer(container)
//                        .setStdin(false)
//                        .setStdout(true)
//                        .connect();
//        InputStream inputStream3 = res3.getStandardOutputStream();
//        inputStream3.read();
//        inputStream3.close();
//        res3.close();
    }
}

package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sSecretService;
import com.my.springboot.demo.utils.PresetYamlReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static test.kubernetes.TestScopeConstant.address;
import static test.kubernetes.TestScopeConstant.token;

public class K8sSecretServiceTest {
    private K8sSecretService service;

    @Before
    public void setUp() {
        service = new K8sSecretService();
    }

    @Test
    public void testList() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        String address = "https://172.16.31.35:6443/";
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InFtWmFudFZvVzdvZTBrVlhaZWJpbWxXQ0I4ZWpVOWVNRGtjYkRzVFVrZFUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImFkbWluLXNlcnZpY2UtYWNjb3VudC10b2tlbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbi1zZXJ2aWNlLWFjY291bnQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJmMTMzZDRlNi0zMDliLTQ0ZTMtYTQzYi0wN2RhZTE4ODVkMjciLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDphZG1pbi1zZXJ2aWNlLWFjY291bnQifQ.wT4RDqqLM7DA51Ood4m95R2Z6plPYA46HhSZMrd2q5d6snkG-0g5RBb_fkGuhWvBAT90_EhbM-JT2DHYx1C0lGTF02sKxRe0OnhsJlSK_W2eiVBSE3KEANc_VbZzTuAfWQeRB7rBHg2ldMl1a1hR4dhHj_koYN9-39v0hRQ-LGuhGgCHjXVIvx-Kr8lgWLf_7zxtlnKv-jjZzVgpDc6QCPEiImyp8zhi4StgJNc6ZUsO-5G1w99VnClYJebmxkBXAwJBvi-qX5rJtekUhaDcLr4BneSFTMz5Aa-l7w1S17shP93aM2hqoZUdGkzsm5z_ddrmi9AgRJxCiE-CExVBKg";
        String namespace = "default";
        System.out.println(service.listSecrets(address, token, namespace, requestMap));
    }

    @Test
    public void testDelete() throws IOException {
        String namespace = "default";

        String secretName = "ceph-secret-dbd92dce-d98c-11f0-8a4e-525400c7831f";
        System.out.println(service.deleteSecret(address, token, namespace, secretName, Map.of()));
    }

    @Test
    public void testCreate() throws IOException {

        Map<String, String> secretData = new HashMap<>();
        String cephUser = "admin";
        String cephUserKey = "AQBvwT9ppGwlEhAA/QaB/JXlSDsVDGCDrMeRUA==";
        String userId = Base64.getEncoder().encodeToString(cephUser.getBytes());
        String userKey = Base64.getEncoder().encodeToString(cephUserKey.getBytes());
        secretData.put("userId", userId);
        secretData.put("userKey", userKey);
        secretData.put("app", "ceph-rbd");
        secretData.put("managerBy", "clout-platform");
        secretData.put("cephClusterId", "dbd92dce-d98c-11f0-8a4e-525400c7831f");
        secretData.put("namespace", "default");
        secretData.put("secretName", "ceph-secret-dbd92dce-d98c-11f0-8a4e-525400c7831f");
        secretData.put("type", "Opaque");

        String replacedYamlContent = PresetYamlReader.readYamlAndReplace("ceph-secret.yaml", secretData);

        String jsonFromYaml = TestScopeUtils.getJsonFromYaml(replacedYamlContent);
        Map<String, String> requestMap = Map.of();
        String address = "https://172.16.31.35:6443";
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6InFtWmFudFZvVzdvZTBrVlhaZWJpbWxXQ0I4ZWpVOWVNRGtjYkRzVFVrZFUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImFkbWluLXNlcnZpY2UtYWNjb3VudC10b2tlbiIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50Lm5hbWUiOiJhZG1pbi1zZXJ2aWNlLWFjY291bnQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJmMTMzZDRlNi0zMDliLTQ0ZTMtYTQzYi0wN2RhZTE4ODVkMjciLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ZGVmYXVsdDphZG1pbi1zZXJ2aWNlLWFjY291bnQifQ.wT4RDqqLM7DA51Ood4m95R2Z6plPYA46HhSZMrd2q5d6snkG-0g5RBb_fkGuhWvBAT90_EhbM-JT2DHYx1C0lGTF02sKxRe0OnhsJlSK_W2eiVBSE3KEANc_VbZzTuAfWQeRB7rBHg2ldMl1a1hR4dhHj_koYN9-39v0hRQ-LGuhGgCHjXVIvx-Kr8lgWLf_7zxtlnKv-jjZzVgpDc6QCPEiImyp8zhi4StgJNc6ZUsO-5G1w99VnClYJebmxkBXAwJBvi-qX5rJtekUhaDcLr4BneSFTMz5Aa-l7w1S17shP93aM2hqoZUdGkzsm5z_ddrmi9AgRJxCiE-CExVBKg";
        String namespace = "default";
        System.out.println(service.createSecret(address, token, namespace, jsonFromYaml, requestMap));
    }
}

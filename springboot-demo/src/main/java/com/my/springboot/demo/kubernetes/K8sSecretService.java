package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.my.springboot.demo.kubernetes.K8sApiMaps.SecretApiMaps.*;


@Service
public class K8sSecretService {
    public String listSecretsWithoutNamespace(String address, String token, Map<String, String> requestMap) {
        String uri = address + LIST_SECRETS_WITHOUT_NAMESPACE;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String listSecrets(String address, String token, String namespaceName, Map<String, String> requestMap) {
        String uri = address + LIST_SECRETS.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getSecret(String address, String token, String namespaceName, String secretName, Map<String, String> requestMap) {
        String uri = address + GET_SECRET.replace("{namespace}", namespaceName).replace("{name}", secretName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String createSecret(String address, String token, String namespaceName, String secretJsonData, Map<String, String> requestMap) {
        String uri = address + CREATE_SECRET.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.postJson(uri, headerMap, requestMap, secretJsonData);
    }

    public String updateSecret(String address, String token, String namespaceName, String secretName, String secretJsonData, Map<String, String> requestMap) {
        String uri = address + UPDATE_SECRET.replace("{namespace}", namespaceName).replace("{name}", secretName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, secretJsonData);
    }

    public String patchSecret(String address, String token, String namespaceName, String secretName, String secretJsonData, Map<String, String> requestMap) {
        String uri = address + PATCH_SECRET.replace("{namespace}", namespaceName).replace("{name}", secretName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.patchJson(uri, headerMap, requestMap, secretJsonData);
    }

    public String deleteSecret(String address, String token, String namespaceName, String secretName, Map<String, String> requestMap) {
        String uri = address + DELETE_SECRET.replace("{namespace}", namespaceName).replace("{name}", secretName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.delete(uri, headerMap, requestMap);
    }
}

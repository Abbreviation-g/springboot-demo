package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class K8sNamespaceService {
    public String listNamespaces(String address, String token, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NamespaceApiMaps.LIST_NAMESPACES;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getNamespace(String address, String token, String namespaceName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NamespaceApiMaps.GET_NAMESPACE.replace("{name}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getNamespaceStatus(String address, String token, String namespaceName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NamespaceApiMaps.GET_NAMESPACE_STATUS.replace("{name}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String createNamespace(String address, String token, String namespaceJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NamespaceApiMaps.CREATE_NAMESPACE;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.postJson(uri, headerMap, requestMap, namespaceJsonData);
    }

    public String updateNamespace(String address, String token, String namespaceName, String namespaceJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NamespaceApiMaps.UPDATE_NAMESPACE.replace("{name}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, namespaceJsonData);
    }

    public String updateNamespaceStatus(String address, String token, String namespaceName, String namespaceJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NamespaceApiMaps.UPDATE_NAMESPACE_STATUS.replace("{name}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, namespaceJsonData);
    }

    public String deleteNamespace(String address, String token, String namespaceName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NamespaceApiMaps.DELETE_NAMESPACE.replace("{name}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.delete(uri, headerMap, requestMap);
    }
}

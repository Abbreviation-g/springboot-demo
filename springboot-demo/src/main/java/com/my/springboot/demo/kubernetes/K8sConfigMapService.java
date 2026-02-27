package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;
import com.my.springboot.demo.utils.K8sConstants;
import java.util.Map;

@Service
public class K8sConfigMapService {
    public String createConfigMap(String address, String token, String namespaceName, String configMapJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.ConfigMapApiMaps.CREATE_CONFIGMAP.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.postJson(uri, headerMap, requestMap, configMapJsonData);
    }

    public String getConfigMap(String address, String token, String namespaceName, String configMapName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.ConfigMapApiMaps.GET_CONFIGMAP.replace("{namespace}", namespaceName).replace("{name}", configMapName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String listConfigMaps(String address, String token, String namespaceName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.ConfigMapApiMaps.LIST_CONFIGMAPS.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String listConfigMapsWithoutNamespace(String address, String token, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.ConfigMapApiMaps.LIST_CONFIGMAPS_WITHOUT_NAMESPACE;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String updateConfigMap(String address, String token, String namespaceName, String configMapName, String configMapJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.ConfigMapApiMaps.UPDATE_CONFIGMAP.replace("{namespace}", namespaceName).replace("{name}", configMapName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, configMapJsonData);
    }

    public String deleteConfigMap(String address, String token, String namespaceName, String configMapName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.ConfigMapApiMaps.DELETE_CONFIGMAP.replace("{namespace}", namespaceName).replace("{name}", configMapName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.delete(uri, headerMap, requestMap);
    }

    public String patchConfigMap(String address, String token, String namespaceName, String configMapName, String configMapJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.ConfigMapApiMaps.PATCH_CONFIGMAP.replace("{namespace}", namespaceName).replace("{name}", configMapName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.patchJson(uri, headerMap, requestMap, configMapJsonData);
    }
}

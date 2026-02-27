package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class K8sStatefulSetService {
    public String createStatefulSet(String address, String token, String namespaceName, String statefulSetJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StatefulSetApiMaps.CREATE_STATEFULSET.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.postJson(uri, headerMap, requestMap, statefulSetJsonData);
    }

    public String getStatefulSet(String address, String token, String namespaceName, String statefulSetName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StatefulSetApiMaps.GET_STATEFULSET.replace("{namespace}", namespaceName).replace("{name}", statefulSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getStatefulSetStatus(String address, String token, String namespaceName, String statefulSetName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StatefulSetApiMaps.GET_STATEFULSET_STATUS.replace("{namespace}", namespaceName).replace("{name}", statefulSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String listStatefulSets(String address, String token, String namespaceName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StatefulSetApiMaps.LIST_STATEFULSETS.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String listStatefulSetsWithoutNamespace(String address, String token, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StatefulSetApiMaps.LIST_STATEFULSETS_WITHOUT_NAMESPACE;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String updateStatefulSet(String address, String token, String namespaceName, String statefulSetName, String statefulSetJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StatefulSetApiMaps.UPDATE_STATEFULSET.replace("{namespace}", namespaceName).replace("{name}", statefulSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, statefulSetJsonData);
    }

    public String deleteStatefulSet(String address, String token, String namespaceName, String statefulSetName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StatefulSetApiMaps.DELETE_STATEFULSET.replace("{namespace}", namespaceName).replace("{name}", statefulSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.delete(uri, headerMap, requestMap);
    }
}

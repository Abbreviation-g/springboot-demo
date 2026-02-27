package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class K8sDaemonSetService {
    public String createDaemonSet(String address, String token, String namespaceName, String daemonSetJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DaemonSetApiMaps.CREATE_DAEMONSET.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.postJson(uri, headerMap, requestMap, daemonSetJsonData);
    }

    public String getDaemonSet(String address, String token, String namespaceName, String daemonSetName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DaemonSetApiMaps.GET_DAEMONSET.replace("{namespace}", namespaceName).replace("{name}", daemonSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getDaemonSetStatus(String address, String token, String namespaceName, String daemonSetName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DaemonSetApiMaps.GET_DAEMONSET_STATUS.replace("{namespace}", namespaceName).replace("{name}", daemonSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String listDaemonSets(String address, String token, String namespaceName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DaemonSetApiMaps.LIST_DAEMONSETS.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String listDaemonSetsWithoutNamespace(String address, String token, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DaemonSetApiMaps.LIST_DAEMONSETS_WITHOUT_NAMESPACE;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String updateDaemonSet(String address, String token, String namespaceName, String daemonSetName, String daemonSetJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DaemonSetApiMaps.UPDATE_DAEMONSET.replace("{namespace}", namespaceName).replace("{name}", daemonSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, daemonSetJsonData);
    }

    public String deleteDaemonSet(String address, String token, String namespaceName, String daemonSetName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DaemonSetApiMaps.DELETE_DAEMONSET.replace("{namespace}", namespaceName).replace("{name}", daemonSetName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.delete(uri, headerMap, requestMap);
    }
}

package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class K8sNodeService {

    public String listNodes(String address, String token, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NodeApiMaps.LIST_NODES;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getNode(String address, String token, String nodeName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NodeApiMaps.GET_NODE.replace("{name}", nodeName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getNodeStatus(String address, String token, String nodeName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NodeApiMaps.GET_NODE_STATUS.replace("{name}", nodeName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String updateNode(String address, String token, String nodeName, String nodeJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NodeApiMaps.UPDATE_NODE.replace("{name}", nodeName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, nodeJsonData);
    }

    public String patchNode(String address, String token, String nodeName, String nodeJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.NodeApiMaps.PATCH_NODE.replace("{name}", nodeName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.patchJson(uri, headerMap, requestMap, nodeJsonData);
    }
}

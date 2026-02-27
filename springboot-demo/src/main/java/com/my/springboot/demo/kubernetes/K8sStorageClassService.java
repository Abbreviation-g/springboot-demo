package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class K8sStorageClassService {

    public String listStorageClasses(String address, String token, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StorageClassApiMaps.LIST_STORAGE_CLASS;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getStorageClass(String address, String token, String name, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StorageClassApiMaps.GET_STORAGE_CLASS.replace("{name}", name);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String createStorageClass(String address, String token, String body, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StorageClassApiMaps.CREATE_STORAGE_CLASS;
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.postJson(uri, headerMap, requestMap, body);
    }

    public String updateStorageClass(String address, String token, String name, String body, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StorageClassApiMaps.UPDATE_STORAGE_CLASS.replace("{name}", name);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.putJson(uri, headerMap, requestMap, body);
    }

    public String deleteStorageClass(String address, String token, String name, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StorageClassApiMaps.DELETE_STORAGE_CLASS.replace("{name}", name);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.delete(uri, headerMap, requestMap);
    }

    public String patchStorageClass(String address, String token, String name, String body, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.StorageClassApiMaps.PATCH_STORAGE_CLASS.replace("{name}", name);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.patchJson(uri, headerMap, requestMap, body);
    }
}

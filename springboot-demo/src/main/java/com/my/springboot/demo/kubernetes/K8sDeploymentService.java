package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class K8sDeploymentService {
    public String createDeployment(String address, String token, String namespaceName, String deploymentJsonData, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DeploymentApiMaps.CREATE_DEPLOYMENT.replace("{namespace}", namespaceName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.postJson(uri, headerMap, requestMap, deploymentJsonData);
    }

    public String getDeployment(String address, String token, String namespaceName, String deploymentName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DeploymentApiMaps.GET_DEPLOYMENT.replace("{namespace}", namespaceName).replace("{name}", deploymentName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }

    public String getDeploymentStatus(String address, String token, String namespaceName, String deploymentName, Map<String, String> requestMap) {
        String uri = address + K8sApiMaps.DeploymentApiMaps.GET_DEPLOYMENT_STATUS.replace("{namespace}", namespaceName).replace("{name}", deploymentName);
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }
}

package com.my.springboot.demo.kubernetes;

import com.my.springboot.demo.utils.K8sConstants;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class K8sCommonService {
    public String version(String address, String token, Map<String, String> requestMap) {
        String uri = address + "/version";
        Map<String, String> headerMap = Map.of(K8sConstants.AUTHORIZATION, "Bearer " + token);
        return K8sHttpUtils.getUrl(uri, headerMap, requestMap);
    }
}

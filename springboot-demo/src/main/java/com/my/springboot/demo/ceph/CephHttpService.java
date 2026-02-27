package com.my.springboot.demo.ceph;

import com.alibaba.fastjson.JSONObject;
import com.my.springboot.demo.utils.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CephHttpService {
    private static final Logger logger = LoggerFactory.getLogger(CephHttpService.class);

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject getForJson(String url, Map<String, String> headerMap, Map<String, String> requestMap) {
        StringBuilder address;
        HttpHeaders headers = getHttpHeaders(headerMap, null);
        if (!CollectionUtils.isEmpty(requestMap)) {
            address = new StringBuilder(url + "?");
            for (String key : requestMap.keySet()) {
                address.append(key).append("=").append(requestMap.get(key)).append("&");
            }
        } else {
            address = new StringBuilder(url);
        }
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<JSONObject> response = restTemplate.exchange(address.toString(), HttpMethod.GET, formEntity, JSONObject.class);
            if (HttpStatus.OK.value() == response.getStatusCode().value()) {
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("调用ceph接口异常", e);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "调用ceph接口异常");
        }
        return null;
    }

    public JSONObject postForJson(String url, Map<String, String> headerMap, JSONObject requestParam) {
        HttpHeaders headers = getHttpHeaders(headerMap, requestParam);

        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(requestParam, headers);
        try {
            ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, requestEntity, JSONObject.class);
            logger.info("HttpUtils statusCode:{}", response.getStatusCode());
            if (HttpStatus.CREATED.value() == response.getStatusCode().value() || HttpStatus.ACCEPTED.value() == response.getStatusCode().value()) {
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("调用ceph接口异常", e);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "调用ceph接口异常");
        }
        return null;
    }

    public boolean putForJson(String url, Map<String, String> headerMap, JSONObject requestParam) {
        HttpHeaders headers = getHttpHeaders(headerMap, requestParam);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestParam.toString(), headers);
        try {
            ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, JSONObject.class);
            logger.info("HttpUtils statusCode:{}", response.getStatusCode());
            if (HttpStatus.ACCEPTED.value() == response.getStatusCodeValue() || HttpStatus.OK.value() == response.getStatusCodeValue()) {
                return true;
            }
        } catch (Exception e) {
            logger.error("调用Ceph接口异常", e);
            return false;
        }
        return false;
    }

    public boolean deleteForJson(String url, Map<String, String> headerMap) {
        HttpHeaders headers = getHttpHeaders(headerMap, null);

        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.DELETE, formEntity, JSONObject.class);
            logger.info("HttpUtils DELETE statusCode: {}", response.getStatusCode());
            if (HttpStatus.ACCEPTED.value() == response.getStatusCodeValue()
                    || HttpStatus.OK.value() == response.getStatusCodeValue()
                    || HttpStatus.NO_CONTENT.value() == response.getStatusCodeValue()) {
                return true;
            }
        } catch (Exception e) {
            logger.error("调用Ceph删除接口异常", e);
            return false;
        }
        return false;
    }

    public String postForJsonRetuenString(String url, Map<String, String> headerMap, JSONObject requestParam) {
        HttpHeaders headers = getHttpHeaders(headerMap, requestParam);

        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(requestParam, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            logger.info("HttpUtils statusCode:{}", response.getStatusCode());
            if (HttpStatus.CREATED.value() == response.getStatusCodeValue() || HttpStatus.ACCEPTED.value() == response.getStatusCodeValue()
                    || HttpStatus.OK.value() == response.getStatusCodeValue()) {
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("调用ceph接口异常", e);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "调用ceph接口异常");
        }
        return null;
    }

    private static HttpHeaders getHttpHeaders(Map<String, String> headerMap, JSONObject requestParam) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(CephConstant.HTTP_ACCEPT, CephConstant.HTTP_ACCEPT_VALUE);
        headers.setContentLength(requestParam != null ? requestParam.toString().length() : 0);

        if (!ObjectUtils.isEmpty(headerMap)) {
            for (String key : headerMap.keySet()) {
                headers.set(key, headerMap.get(key));
            }
        }
        return headers;
    }
}

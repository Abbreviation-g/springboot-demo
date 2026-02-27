package com.my.springboot.demo.ceph;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.springboot.demo.utils.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CephService {
    private static final Logger logger = LoggerFactory.getLogger(CephService.class);

    @Autowired
    private CephHttpService cephHttpService;

    public String auth(String baseUrl, String account, String password) {
        String url = baseUrl + CephConstant.HTTP_API_AUTH;
        JSONObject requestJson = new JSONObject();
        requestJson.put(CephConstant.CEPH_USERNAME, account);
        requestJson.put(CephConstant.CEPH_PASSWORD, password);
        JSONObject resultJson = cephHttpService.postForJson(url, new HashMap<>(), requestJson);
        logger.info("ceph auth result:\n {}", resultJson);
        if (Objects.isNull(resultJson) || !resultJson.containsKey(CephConstant.HTTP_AUTH_TOKEN) || !StringUtils.hasText(resultJson.getString(CephConstant.HTTP_AUTH_TOKEN))) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取ceph token失败");
        }
        return resultJson.getString(CephConstant.HTTP_AUTH_TOKEN);
    }

    public JSONObject queryInfo(String baseUrl, String token) {
        String url = baseUrl + CephConstant.HTTP_API_HEALTH;
        Map<String, String> hearderMap = getHeadMap(token);
        return cephHttpService.getForJson(url, hearderMap, new HashMap<>());
    }

    public CephCapitalResDTO queryCapacity(String baseUrl, String token) {
        String url = baseUrl + CephConstant.HTTP_API_CAPACITY;
        Map<String, String> hearderMap = getHeadMap(token);
        JSONObject resultJson = cephHttpService.getForJson(url, hearderMap, null);
        return JSONObject.parseObject(resultJson.toJSONString(), CephCapitalResDTO.class);
    }

    public String queryCephKey(String baseUrl, String token) {
        String url = baseUrl + CephConstant.HTTP_API_USER_EXPORT;
        Map<String, String> hearderMap = getHeadMap(token);
        JSONObject requestJson = JSONObject.parseObject("{\"entities\":[\"client.admin\"]}");
        String resultJson = cephHttpService.postForJsonRetuenString(url, hearderMap, requestJson);
        logger.info("queryCephKey result:{}", resultJson);
        return resultJson;
    }

    private static Map<String, String> getHeadMap(String token) {
        return Map.of(CephConstant.HTTP_COOKIE, CephConstant.HTTP_COOKIE_PRE_VALUE + token);
    }
    public void createPool(String baseUrl, String token, String cephPoolName, Integer pgNum, Integer pgpNum, String poolType, String crushRule) {
        logger.info("创建存储池, poolName={}, crushRule={}",
                cephPoolName, crushRule);
        String url = baseUrl + CephConstant.HTTP_API_CREATE_POOL;
        JSONObject requestJson = new JSONObject();
        requestJson.put("pool", cephPoolName);
        requestJson.put("pg_num", pgNum);
        requestJson.put("pgp_num", pgpNum);
        requestJson.put("pool_type", poolType);
        requestJson.put("crush_rule", crushRule);

        Map<String, String> hearderMap = new HashMap<>();
        hearderMap.put("cookie", "token=" + token);
        JSONObject resultJson = cephHttpService.postForJson(url, hearderMap, requestJson);
        logger.info("创建存储池结果:{}", resultJson);
    }

    public void createPool(String baseUrl, String token, String poolName) {
        //获取token
        logger.info("创建存储池:{}", poolName);
        String url = baseUrl + CephConstant.HTTP_API_CREATE_POOL;
        JSONObject requestJson = new JSONObject();
        requestJson.put("pool", poolName);
        requestJson.put("pool_type", "replicated");
        requestJson.put("pg_autoscale_mode", "on");
        requestJson.put("pg_num", 1);
        requestJson.put("rule_name", "replicated_rule");
        requestJson.put("size", 3);
        Map<String, String> hearderMap = new HashMap<>();
        hearderMap.put("cookie", "token=" + token);
        JSONObject resultJson = cephHttpService.postForJson(url, hearderMap, requestJson);
        logger.info("创建存储池结果:{}", resultJson);
    }

    public void createQuotas(String baseUrl, String token, CephQuotasDTO quotasDTO, String poolName) {
        String url = baseUrl + "/api/pool/" + poolName;
        Map<String, String> hearderMap = getHeadMap(token);
        JSONObject quotasJson = (JSONObject) JSON.toJSON(quotasDTO);
        logger.info("创建存储池配额 quotasDTO:{}", quotasJson);
        boolean result = cephHttpService.putForJson(url, hearderMap, quotasJson);
        logger.info("创建存储池配额结果:{}", result);
        if (!result) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建存储池配额失败");
        }
    }

    public Integer queryDefaultReplicaSize(String baseUrl, String token) {
        String url = baseUrl + CephConstant.HTTP_API_CLUSTER_CONF_DEFAULT_SIZE;
        Map<String, String> hearderMap = getHeadMap(token);
        try {
            JSONObject resultJson = cephHttpService.getForJson(url, hearderMap, null);
            if (resultJson != null && resultJson.containsKey("default")) {
                Integer defaultSize = resultJson.getInteger("default");
                logger.info("查询 Ceph 默认副本数成功: osd_pool_default_size={}", defaultSize);
                return defaultSize;
            }
        } catch (Exception e) {
            logger.warn("查询 Ceph 默认副本数失败, 将使用配置的默认值, error: {}", e.getMessage());
        }
        return null;
    }

    public void quotas(String baseUrl, String token, CephQuotasDTO quotasDTO, String poolName) {
        String url = baseUrl + "/api/pool/" + poolName;
        Map<String, String> hearderMap = new HashMap<>();
        hearderMap.put(CephConstant.HTTP_COOKIE, CephConstant.HTTP_COOKIE_PRE_VALUE + token);
        logger.info("创建存储池配额, poolName={}, url={}", poolName, url);
        boolean result = cephHttpService.putForJson(url, hearderMap, (JSONObject) JSONObject.toJSON(quotasDTO));
        logger.info("创建存储池配额结果: poolName={}, result={}", poolName, result);
        if (!result) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建存储池配额失败");
        }
    }

    public void deletePool(String baseUrl, String token, String poolName) {
        try {
            logger.info("删除Ceph存储池, poolName={}", poolName);
            String url = baseUrl + "/api/pool/" + poolName;
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put(CephConstant.HTTP_COOKIE, CephConstant.HTTP_COOKIE_PRE_VALUE + token);

            boolean result = cephHttpService.deleteForJson(url, headerMap);
            if (result) {
                logger.info("删除Ceph存储池成功, poolName={}", poolName);
            } else {
                logger.warn("删除Ceph存储池失败或池不存在, poolName={}", poolName);
            }
        } catch (Exception e) {
            logger.error("删除Ceph存储池异常, poolName={}, error={}", poolName, e.getMessage(), e);
            // 不抛出异常，避免影响资源池删除流程
        }
    }

    public JSONObject getPool(String baseUrl, String token, String poolName) {
        try {
            logger.info("查询Ceph存储池, poolName={}", poolName);
            String url = baseUrl + "/api/pool/" + poolName;
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put(CephConstant.HTTP_COOKIE, CephConstant.HTTP_COOKIE_PRE_VALUE + token);

            JSONObject result = cephHttpService.getForJson(url, headerMap, Map.of());
            logger.info("查询Ceph存储池成功, poolName={}, result=\n{}", poolName, result);
            return result;
        } catch (Exception e) {
            logger.error("查询Ceph存储池成功, poolName={}, error={}", poolName, e.getMessage(), e);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询Ceph存储池失败");
        }
    }

}

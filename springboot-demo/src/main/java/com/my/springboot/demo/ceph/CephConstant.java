package com.my.springboot.demo.ceph;

/**
 * @author wangxinghao
 * @version 1.0.0
 * @className CephConstant
 * @date 2025/4/27
 * @description ceph 常量
 */
public class CephConstant {

    public static final String HTTP_COOKIE = "cookie";

    public static final String HTTP_COOKIE_PRE_VALUE = "token=";

    //    "Accept", "application/vnd.ceph.api.v1.0+json"
    public static final String HTTP_ACCEPT = "Accept";
    public static final String HTTP_ACCEPT_VALUE = "application/vnd.ceph.api.v1.0+json";

    public static final String HTTP_API_HEALTH = "/api/health/full";

    public static final String HTTP_API_AUTH = "/api/auth";

    public static final String HTTP_API_CAPACITY = "/api/health/get_cluster_capacity";

    public static final String HTTP_API_CREATE_POOL = "/api/pool";

    public static final String HTTP_API_USER_EXPORT = "/api/cluster/user/export";

    /**
     * 查询默认副本数配置 API
     */
    public static final String HTTP_API_CLUSTER_CONF_DEFAULT_SIZE = "/api/cluster_conf/osd_pool_default_size";

    public static final String HTTP_AUTH_TOKEN = "token";

    public static final String CEPH_USERNAME = "username";

    public static final String CEPH_PASSWORD = "password";
}

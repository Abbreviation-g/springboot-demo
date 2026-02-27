package com.my.springboot.demo.kubernetes;

public class K8sApiMaps {
    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/cluster-resources/node-v1/
     */
    public static class NodeApiMaps {
        /**
         * get 读取指定节点
         * HTTP 请求
         * GET /api/v1/nodes/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * pretty (路径参数): string
         */
        public static final String GET_NODE = "/api/v1/nodes/{name}";
        /**
         * get 读取指定节点的状态
         * HTTP 请求
         * GET /api/v1/nodes/{name}/status
         * <p>
         * 参数
         * name (路径参数): string，必需
         * pretty (查询参数): string
         */
        public static final String GET_NODE_STATUS = "/api/v1/nodes/{name}/status";
        /**
         * list 列出或监视节点类型的对象
         * HTTP 请求
         * GET /api/v1/nodes
         * <p>
         * 参数
         * allowWatchBookmarks (查询参数): boolean
         * continue (查询参数): string
         * fieldSelector (查询参数): string
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         * watch (查询参数): boolean
         */
        public static final String LIST_NODES = "/api/v1/nodes";
        /**
         * create 创建一个节点
         * HTTP 请求
         * POST /api/v1/nodes
         * <p>
         * 参数
         * body: Node，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String CREATE_NODE = "/api/v1/nodes";
        /**
         * update 替换指定节点
         * HTTP 请求
         * PUT /api/v1/nodes/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * body: Node，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String UPDATE_NODE = "/api/v1/nodes/{name}";
        /**
         * update 替换指定节点的状态
         * HTTP 请求
         * PUT /api/v1/nodes/{name}/status
         * <p>
         * 参数
         * name (路径参数): string，必需
         * body: Node，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String UPDATE_NODE_STATUS = "/api/v1/nodes/{name}/status";
        /**
         * patch 部分更新指定节点
         * HTTP 请求
         * PATCH /api/v1/nodes/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * body: Patch，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * force (查询参数): boolean
         * pretty (查询参数): string
         */
        public static final String PATCH_NODE = "/api/v1/nodes/{name}";
        /**
         * patch 部分更新指定节点的状态
         * HTTP 请求
         * PATCH /api/v1/nodes/{name}/status
         * <p>
         * 参数
         * name (路径参数): string，必需
         * body: Patch，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * force (查询参数): boolean
         * pretty (查询参数): string
         */
        public static final String PATCH_NODE_STATUS = "/api/v1/nodes/{name}/status";
        /**
         * delete 删除一个节点
         * HTTP 请求
         * DELETE /api/v1/nodes/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * body: DeleteOptions
         * dryRun (查询参数): string
         * gracePeriodSeconds (查询参数): integer
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean
         * pretty (查询参数): string
         * propagationPolicy (查询参数): string
         */
        public static final String DELETE_NODE = "/api/v1/nodes/{name}";
        /**
         * deletecollection 删除节点的集合
         * HTTP 请求
         * DELETE /api/v1/nodes
         * <p>
         * continue (查询参数): string
         * dryRun (查询参数): string
         * fieldSelector (查询参数): string
         * gracePeriodSeconds (查询参数): integer
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * propagationPolicy (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         */
        public static final String DELETE_NODE_COLLECTION = "/api/v1/nodes";
    }

    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/cluster-resources/namespace-v1/
     */
    public static class NamespaceApiMaps {
        /**
         * get 读取指定的 Namespace
         * HTTP 请求
         * GET /api/v1/namespaces/{name}
         * <p>
         * 参数
         * name (路径参数)：string，必需
         * pretty (查询参数)：string
         */
        public static final String GET_NAMESPACE = "/api/v1/namespaces/{name}";
        /**
         * get 读取指定 Namespace 的状态
         * HTTP 请求
         * GET /api/v1/namespaces/{name}/status
         * <p>
         * 参数
         * name (路径参数)：string，必需
         * pretty (查询参数)：string
         */
        public static final String GET_NAMESPACE_STATUS = "/api/v1/namespaces/{name}/status";
        /**
         * list 列出或者检查类别为 Namespace 的对象
         * HTTP 请求
         * GET /api/v1/namespaces
         * <p>
         * 参数
         * allowWatchBookmarks (查询参数)：boolean
         * continue (查询参数)：string
         * fieldSelector (查询参数)：string
         * labelSelector (查询参数)：string
         * limit (查询参数)：integer
         * pretty (查询参数)：string
         * resourceVersion (查询参数)：string
         * resourceVersionMatch (查询参数)：string
         * timeoutSeconds (查询参数)：integer
         * watch (查询参数)：boolean
         */
        public static final String LIST_NAMESPACES = "/api/v1/namespaces";
        /**
         * create 创建一个 Namespace
         * HTTP 请求
         * POST /api/v1/namespaces
         * <p>
         * 参数
         * body: Namespace，必需
         * dryRun (查询参数)：string
         * fieldManager (查询参数)：string
         * fieldValidation (查询参数)：string
         * pretty (查询参数)：string
         */
        public static final String CREATE_NAMESPACE = "/api/v1/namespaces";
        /**
         * update 替换指定的 Namespace
         * HTTP 请求
         * PUT /api/v1/namespaces/{name}
         * <p>
         * 参数
         * name (路径参数)：string，必需
         * body: Namespace，必需
         * dryRun (查询参数)：string
         * fieldManager (查询参数)：string
         * fieldValidation (查询参数)：string
         * pretty (查询参数)：string
         */
        public static final String UPDATE_NAMESPACE = "/api/v1/namespaces/{name}";
        /**
         * update 替换指定 Namespace 的状态
         * HTTP 请求
         * PUT /api/v1/namespaces/{name}/status
         * <p>
         * 参数
         * name (路径阐述)：string，必需
         * Namespace 的名称
         * body: Namespace，必需
         * dryRun (查询参数)：string
         * fieldManager (查询参数)：string
         * fieldValidation (查询参数)：string
         * pretty (查询参数)：string
         */
        public static final String UPDATE_NAMESPACE_STATUS = "/api/v1/namespaces/{name}/status";
        /**
         * delete 删除一个 Namespace
         * HTTP 请求
         * DELETE /api/v1/namespaces/{name}
         * <p>
         * 参数
         * name (路径参数)：string，必需
         * body: DeleteOptions
         * dryRun (查询参数)：string
         * gracePeriodSeconds (查询参数)：integer
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数)：boolean
         * pretty (查询参数)：string
         * propagationPolicy (查询参数)：string
         */
        public static final String DELETE_NAMESPACE = "/api/v1/namespaces/{name}";
    }

    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/workload-resources/deployment-v1/
     */
    public static class DeploymentApiMaps {
        /**
         * get 读取指定的 Deployment
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/deployments/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * pretty (查询参数): string
         */
        public static final String GET_DEPLOYMENT = "/apis/apps/v1/namespaces/{namespace}/deployments/{name}";
        /**
         * get 读取指定的 Deployment 的状态
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/deployments/{name}/status
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * pretty (查询参数): string
         */
        public static final String GET_DEPLOYMENT_STATUS = "/apis/apps/v1/namespaces/{namespace}/deployments/{name}/status";
        /**
         * list 列出或监视 Deployment 类别的对象
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/deployments
         * <p>
         * 参数
         * namespace (路径参数): string，必需
         * allowWatchBookmarks (查询参数): boolean
         * continue (查询参数): string
         * fieldSelector (查询参数): string
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         * watch (查询参数): boolean
         */
        public static final String LIST_DEPLOYMENTS = "/apis/apps/v1/namespaces/{namespace}/deployments";
        /**
         * list 列出或监视 Deployment 类别的对象
         * HTTP 请求
         * GET /apis/apps/v1/deployments
         * <p>
         * 参数
         * allowWatchBookmarks (查询参数): boolean
         * continue (查询参数): string
         * fieldSelector (查询参数): string
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         * watch (查询参数): boolean
         */
        public static final String LIST_DEPLOYMENTS_ALL_NAMESPACES = "/apis/apps/v1/deployments";
        /**
         * create 创建 Deployment
         * HTTP 请求
         * POST /apis/apps/v1/namespaces/{namespace}/deployments
         * <p>
         * 参数
         * namespace (路径参数): string，必需
         * body: Deployment，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String CREATE_DEPLOYMENT = "/apis/apps/v1/namespaces/{namespace}/deployments";
        /**
         * update 替换指定的 Deployment
         * HTTP 请求
         * PUT /apis/apps/v1/namespaces/{namespace}/deployments/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * body: Deployment，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String UPDATE_DEPLOYMENT = "/apis/apps/v1/namespaces/{namespace}/deployments/{name}";
        /**
         * update 替换指定的 Deployment 的状态
         * HTTP 请求
         * PUT /apis/apps/v1/namespaces/{namespace}/deployments/{name}/status
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * body: Deployment，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String UPDATE_DEPLOYMENT_STATUS = "/apis/apps/v1/namespaces/{namespace}/deployments/{name}/status";
        /**
         * delete 删除 Deployment
         * HTTP 请求
         * DELETE /apis/apps/v1/namespaces/{namespace}/deployments/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * body: DeleteOptions
         * dryRun (查询参数): string
         * gracePeriodSeconds (查询参数): integer
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean
         * pretty (查询参数): string
         * propagationPolicy (查询参数): string
         */
        public static final String DELETE_DEPLOYMENT = "/apis/apps/v1/namespaces/{namespace}/deployments/{name}";
    }

    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/workload-resources/stateful-set-v1/
     */
    public static class StatefulSetApiMaps {
        /**
         * get 读取指定的 StatefulSet
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/statefulsets/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * pretty (查询参数): string
         */
        public static final String GET_STATEFULSET = "/apis/apps/v1/namespaces/{namespace}/statefulsets/{name}";
        /**
         * get 读取指定 StatefulSet 的状态
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/statefulsets/{name}/status
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * pretty (查询参数): string
         */
        public static final String GET_STATEFULSET_STATUS = "/apis/apps/v1/namespaces/{namespace}/statefulsets/{name}/status";
        /**
         * list 列出或监视 StatefulSet 类型的对象
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/statefulsets
         * <p>
         * 参数
         * namespace (路径参数): string，必需
         * allowWatchBookmarks (查询参数): boolean
         * continue (查询参数): string
         * fieldSelector (查询参数): string
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         * watch (查询参数): boolean
         */
        public static final String LIST_STATEFULSETS = "/apis/apps/v1/namespaces/{namespace}/statefulsets";
        /**
         * list 列出或监视 StatefulSet 类型的对象
         * HTTP 请求
         * GET /apis/apps/v1/statefulsets
         * <p>
         * 参数
         * allowWatchBookmarks (查询参数): boolean
         * continue (查询参数): string
         * fieldSelector (查询参数): string
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         * watch (查询参数): boolean
         */
        public static final String LIST_STATEFULSETS_WITHOUT_NAMESPACE = "/apis/apps/v1/statefulsets";
        /**
         * create 创建一个 StatefulSet
         * HTTP 请求
         * POST /apis/apps/v1/namespaces/{namespace}/statefulsets
         * <p>
         * 参数
         * namespace (路径参数): string，必需
         * body: StatefulSet，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String CREATE_STATEFULSET = "/apis/apps/v1/namespaces/{namespace}/statefulsets";
        /**
         * update 替换指定的 StatefulSet
         * HTTP 请求
         * PUT /apis/apps/v1/namespaces/{namespace}/statefulsets/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * body: StatefulSet，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String UPDATE_STATEFULSET = "/apis/apps/v1/namespaces/{namespace}/statefulsets/{name}";
        /**
         * delete 删除一个 StatefulSet
         * HTTP 请求
         * DELETE /apis/apps/v1/namespaces/{namespace}/statefulsets/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * body: DeleteOptions
         * dryRun (查询参数): string
         * gracePeriodSeconds (查询参数): integer
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean
         * pretty (查询参数): string
         * propagationPolicy (查询参数): string
         */
        public static final String DELETE_STATEFULSET = "/apis/apps/v1/namespaces/{namespace}/statefulsets/{name}";
    }

    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/workload-resources/daemon-set-v1/
     */
    public static class DaemonSetApiMaps {
        /**
         * get 读取指定的 DaemonSet
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/daemonsets/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * pretty (查询参数): string
         */
        public static final String GET_DAEMONSET = "/apis/apps/v1/namespaces/{namespace}/daemonsets/{name}";
        /**
         * get 读取指定的 DaemonSet 的状态
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/daemonsets/{name}/status
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * pretty (查询参数): string
         */
        public static final String GET_DAEMONSET_STATUS = "/apis/apps/v1/namespaces/{namespace}/daemonsets/{name}/status";
        /**
         * list 列出或监视 DaemonSet 类型的对象
         * HTTP 请求
         * GET /apis/apps/v1/namespaces/{namespace}/daemonsets
         * <p>
         * 参数
         * namespace (路径参数): string，必需
         * allowWatchBookmarks (查询参数): boolean
         * continue (查询参数): string
         * fieldSelector (查询参数): string
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         * watch (查询参数): boolean
         */
        public static final String LIST_DAEMONSETS = "/apis/apps/v1/namespaces/{namespace}/daemonsets";

        /**
         * list 列出或监视 DaemonSet 类型的对象
         * HTTP 请求
         * GET /apis/apps/v1/daemonsets
         * <p>
         * 参数
         * allowWatchBookmarks (查询参数): boolean
         * continue (查询参数): string
         * fieldSelector (查询参数): string
         * labelSelector (查询参数): string
         * limit (查询参数): integer
         * pretty (查询参数): string
         * resourceVersion (查询参数): string
         * resourceVersionMatch (查询参数): string
         * sendInitialEvents (查询参数): boolean
         * timeoutSeconds (查询参数): integer
         * watch (查询参数): boolean
         */
        public static final String LIST_DAEMONSETS_WITHOUT_NAMESPACE = "/apis/apps/v1/daemonsets";
        /**
         * create 创建一个 DaemonSet
         * HTTP 请求
         * POST /apis/apps/v1/namespaces/{namespace}/daemonsets
         * <p>
         * 参数
         * namespace (路径参数): string，必需
         * body: DaemonSet，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String CREATE_DAEMONSET = "/apis/apps/v1/namespaces/{namespace}/daemonsets";
        /**
         * update 替换指定的 DaemonSet
         * HTTP 请求
         * PUT /apis/apps/v1/namespaces/{namespace}/daemonsets/{name}
         * <p>
         * 参数
         * name (路径参数):
         * namespace (路径参数): string，必需
         * body: DaemonSet，必需
         * dryRun (查询参数): string
         * fieldManager (查询参数): string
         * fieldValidation (查询参数): string
         * pretty (查询参数): string
         */
        public static final String UPDATE_DAEMONSET = "/apis/apps/v1/namespaces/{namespace}/daemonsets/{name}";
        /**
         * delete 删除一个 DaemonSet
         * HTTP 请求
         * DELETE /apis/apps/v1/namespaces/{namespace}/daemonsets/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需
         * namespace (路径参数): string，必需
         * body: DeleteOptions
         * dryRun (查询参数): string
         * gracePeriodSeconds (查询参数): integer
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean
         * pretty (查询参数): string
         * propagationPolicy (查询参数): string
         */
        public static final String DELETE_DAEMONSET = "/apis/apps/v1/namespaces/{namespace}/daemonsets/{name}";
    }

    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/config-and-storage-resources/config-map-v1/
     */
    public static class ConfigMapApiMaps {
        /**
         * get 读取指定的 ConfigMap
         * HTTP 请求
         * GET /api/v1/namespaces/{namespace}/configmaps/{name}
         * 参数
         * name (路径参数): string，必需 ConfigMap 的名称
         * namespace (路径参数): string，必需 namespace
         * pretty (查询参数): string pretty
         * 响应
         * 200 (ConfigMap): OK
         * 401: Unauthorized
         */
        public static final String GET_CONFIGMAP = "/api/v1/namespaces/{namespace}/configmaps/{name}";
        /**
         * list 列出或观测类别为 ConfigMap 的对象
         * HTTP 请求
         * GET /api/v1/namespaces/{namespace}/configmaps
         * <p>
         * 参数
         * namespace (路径参数): string，必需 namespace
         * allowWatchBookmarks (查询参数): boolean allowWatchBookmarks
         * continue (查询参数): string continue
         * fieldSelector (查询参数): string fieldSelector
         * labelSelector (查询参数): string labelSelector
         * limit (查询参数): integer limit
         * pretty (查询参数): string pretty
         * resourceVersion (查询参数): string resourceVersion
         * resourceVersionMatch (查询参数): string resourceVersionMatch
         * sendInitialEvents (查询参数): boolean sendInitialEvents
         * timeoutSeconds (查询参数): integer timeoutSeconds
         * watch (查询参数): boolean watch
         * 响应
         * 200 (ConfigMapList): OK
         * 401: Unauthorized
         */
        public static final String LIST_CONFIGMAPS = "/api/v1/namespaces/{namespace}/configmaps";
        /**
         * list 列出或观测类别为 ConfigMap 的对象
         * HTTP 请求
         * GET /api/v1/configmaps
         * <p>
         * 参数
         * allowWatchBookmarks (查询参数): boolean allowWatchBookmarks
         * continue (查询参数): string continue
         * fieldSelector (查询参数): string fieldSelector
         * labelSelector (查询参数): string labelSelector
         * limit (查询参数): integer limit
         * pretty (查询参数): string pretty
         * resourceVersion (查询参数): string resourceVersion
         * resourceVersionMatch (查询参数): string resourceVersionMatch
         * sendInitialEvents (查询参数): boolean sendInitialEvents
         * timeoutSeconds (查询参数): integer timeoutSeconds
         * watch (查询参数): boolean watch
         * <p>
         * 响应
         * 200 (ConfigMapList): OK
         * 401: Unauthorized
         */
        public static final String LIST_CONFIGMAPS_WITHOUT_NAMESPACE = "/api/v1/configmaps";

        /**
         * create 创建 ConfigMap
         * HTTP 请求
         * POST /api/v1/namespaces/{namespace}/configmaps
         * <p>
         * 参数
         * namespace (路径参数): string，必需 namespace
         * body: ConfigMap，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * pretty (查询参数): string pretty
         * 响应
         * 200 (ConfigMap): OK
         * 201 (ConfigMap): Created
         * 202 (ConfigMap): Accepted
         * 401: Unauthorized
         */
        public static final String CREATE_CONFIGMAP = "/api/v1/namespaces/{namespace}/configmaps";

        /**
         * update 替换指定的 ConfigMap
         * HTTP 请求
         * PUT /api/v1/namespaces/{namespace}/configmaps/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需 ConfigMap 的名称
         * namespace (路径参数): string，必需 namespace
         * body: ConfigMap，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * pretty (查询参数): string pretty
         * 响应
         * 200 (ConfigMap): OK
         * 201 (ConfigMap): Created
         * 401: Unauthorized
         */
        public static final String UPDATE_CONFIGMAP = "/api/v1/namespaces/{namespace}/configmaps/{name}";
        /**
         * delete 删除 ConfigMap
         * HTTP 请求
         * DELETE /api/v1/namespaces/{namespace}/configmaps/{name}
         * <p>
         * 参数
         * name (路径参数): string，必需 ConfigMap 的名称
         * namespace (路径参数): string，必需 namespace
         * body: DeleteOptions
         * dryRun (查询参数): string dryRun
         * gracePeriodSeconds (查询参数): integer gracePeriodSeconds
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean ignoreStoreReadErrorWithClusterBreakingPotential
         * pretty (查询参数): string pretty
         * propagationPolicy (查询参数): string propagationPolicy
         * 响应
         * 200 (Status): OK
         * 202 (Status): Accepted
         * 401: Unauthorized
         */
        public static final String DELETE_CONFIGMAP = "/api/v1/namespaces/{namespace}/configmaps/{name}";
        /**
         * patch 部分更新指定的 ConfigMap
         * HTTP 请求
         * PATCH /api/v1/namespaces/{namespace}/configmaps/{name}
         * 参数
         * name (路径参数): string，必需 ConfigMap 的名称
         * namespace (路径参数): string，必需 namespace
         * body: Patch，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * force (查询参数): boolean force
         * pretty (查询参数): string pretty
         * 响应
         * 200 (ConfigMap): OK
         * 201 (ConfigMap): Created
         * 401: Unauthorized
         */
        public static final String PATCH_CONFIGMAP = "/api/v1/namespaces/{namespace}/configmaps/{name}";
    }

    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/config-and-storage-resources/secret-v1/
     */
    public static class SecretApiMaps {
        /**
         * get 读取指定的 Secret
         * HTTP 请求
         * GET /api/v1/namespaces/{namespace}/secrets/{name}
         * 参数
         * name (路径参数): string，必需 Secret 的名称。
         * namespace (路径参数): string，必需 namespace
         * pretty (查询参数): string pretty
         * 响应
         * 200 (Secret): OK
         * 401: Unauthorized
         */
        public static final String GET_SECRET = "/api/v1/namespaces/{namespace}/secrets/{name}";
        /**
         * list 列举或观测类别为 Secret 的对象
         * HTTP 请求
         * GET /api/v1/namespaces/{namespace}/secrets
         * <p>
         * 参数
         * namespace (路径参数): string，必需 namespace
         * allowWatchBookmarks (查询参数): boolean allowWatchBookmarks
         * continue (查询参数): string continue
         * fieldSelector (查询参数): string fieldSelector
         * labelSelector (查询参数): string labelSelector
         * limit (查询参数): integer limit
         * pretty (查询参数): string pretty
         * resourceVersion (查询参数): string resourceVersion
         * resourceVersionMatch (查询参数): string resourceVersionMatch
         * sendInitialEvents (查询参数): boolean sendInitialEvents
         * timeoutSeconds (查询参数): integer timeoutSeconds
         * watch (查询参数): boolean watch
         * 响应
         * 200 (SecretList): OK
         * 401: Unauthorized
         */
        public static final String LIST_SECRETS = "/api/v1/namespaces/{namespace}/secrets";

        /**
         * list 列举或观测类别为 Secret 的对象
         * HTTP 请求
         * GET /api/v1/secrets
         * 参数
         * allowWatchBookmarks (查询参数): boolean allowWatchBookmarks
         * continue (查询参数): string continue
         * fieldSelector (查询参数): string fieldSelector
         * labelSelector (查询参数): string labelSelector
         * limit (查询参数): integer limit
         * pretty (查询参数): string pretty
         * resourceVersion (查询参数): string resourceVersion
         * resourceVersionMatch (查询参数): string resourceVersionMatch
         * sendInitialEvents (查询参数): boolean sendInitialEvents
         * timeoutSeconds (查询参数): integer timeoutSeconds
         * watch (查询参数): boolean watch
         * 响应
         * 200 (SecretList): OK
         * 401: Unauthorized
         */
        public static final String LIST_SECRETS_WITHOUT_NAMESPACE = "/api/v1/secrets";

        /**
         * create 创建 Secret
         * HTTP 请求
         * POST /api/v1/namespaces/{namespace}/secrets
         * 参数
         * namespace (路径参数): string，必需 namespace
         * body: Secret，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * pretty (查询参数): string pretty
         * 响应
         * 200 (Secret): OK
         * 201 (Secret): Created
         * 202 (Secret): Accepted
         * 401: Unauthorized
         */
        public static final String CREATE_SECRET = "/api/v1/namespaces/{namespace}/secrets";

        /**
         * update 替换指定的 Secret
         * HTTP 请求
         * PUT /api/v1/namespaces/{namespace}/secrets/{name}
         * 参数
         * name (路径参数): string，必需 Secret 的名称。
         * namespace (路径参数): string，必需 namespace
         * body: Secret，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * pretty (查询参数): string pretty
         * 响应
         * 200 (Secret): OK
         * 201 (Secret): Created
         * 401: Unauthorized
         */
        public static final String UPDATE_SECRET = "/api/v1/namespaces/{namespace}/secrets/{name}";
        /**
         * patch 部分更新指定的 Secret
         * HTTP 请求
         * PATCH /api/v1/namespaces/{namespace}/secrets/{name}
         * 参数
         * name (路径参数): string，必需 Secret 的名称。
         * namespace (路径参数): string，必需 namespace
         * body: Patch，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * force (查询参数): boolean force
         * pretty (查询参数): string pretty
         * 响应
         * 200 (Secret): OK
         * 201 (Secret): Created
         * 401: Unauthorized
         */
        public static final String PATCH_SECRET = "/api/v1/namespaces/{namespace}/secrets/{name}";
        /**
         * delete 删除 Secret
         * HTTP 请求
         * DELETE /api/v1/namespaces/{namespace}/secrets/{name}
         * 参数
         * name (路径参数): string，必需 Secret 的名称。
         * namespace (路径参数): string，必需 namespace
         * body: DeleteOptions
         * dryRun (查询参数): string dryRun
         * gracePeriodSeconds (查询参数): integer gracePeriodSeconds
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean ignoreStoreReadErrorWithClusterBreakingPotential
         * pretty (查询参数): string pretty
         * propagationPolicy (查询参数): string propagationPolicy
         * 响应
         * 200 (Status): OK
         * 202 (Status): Accepted
         * 401: Unauthorized
         */
        public static final String DELETE_SECRET = "/api/v1/namespaces/{namespace}/secrets/{name}";
    }

    /**
     * https://kubernetes.io/zh-cn/docs/reference/kubernetes-api/config-and-storage-resources/storage-class-v1/
     */
    public static class StorageClassApiMaps {
        /**
         * get 读取指定的 StorageClass
         * HTTP 请求
         * GET /apis/storage.k8s.io/v1/storageclasses/{name}
         * 参数
         * name (路径参数): string，必需 StorageClass 的名称。
         * pretty (查询参数): string pretty
         * 响应
         * 200 (StorageClass): OK
         * 401: Unauthorized
         */
        public static final String GET_STORAGE_CLASS = "/apis/storage.k8s.io/v1/storageclasses/{name}";
        /**
         * list 列举或观测类别为 StorageClass 的对象
         * HTTP 请求
         * GET /apis/storage.k8s.io/v1/storageclasses
         * 参数
         * allowWatchBookmarks (查询参数): boolean allowWatchBookmarks
         * continue (查询参数): string continue
         * fieldSelector (查询参数): string fieldSelector
         * labelSelector (查询参数): string labelSelector
         * limit (查询参数): integer limit
         * pretty (查询参数): string pretty
         * resourceVersion (查询参数): string resourceVersion
         * resourceVersionMatch (查询参数): string resourceVersionMatch
         * sendInitialEvents (查询参数): boolean sendInitialEvents
         * timeoutSeconds (查询参数): integer timeoutSeconds
         * watch (查询参数): boolean watch
         * 响应
         * 200 (StorageClassList): OK
         * 401: Unauthorized
         */
        public static final String LIST_STORAGE_CLASS = "/apis/storage.k8s.io/v1/storageclasses";
        /**
         * create 创建 StorageClass
         * HTTP 请求
         * POST /apis/storage.k8s.io/v1/storageclasses
         * 参数
         * body: StorageClass，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * pretty (查询参数): string pretty
         * 响应
         * 200 (StorageClass): OK
         * 201 (StorageClass): Created
         * 202 (StorageClass): Accepted
         * 401: Unauthorized
         */
        public static final String CREATE_STORAGE_CLASS = "/apis/storage.k8s.io/v1/storageclasses";
        /**
         * update 替换指定的 StorageClass
         * HTTP 请求
         * PUT /apis/storage.k8s.io/v1/storageclasses/{name}
         * 参数
         * name (路径参数): string，必需 StorageClass 的名称。
         * body: StorageClass，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * pretty (查询参数): string pretty
         * 响应
         * 200 (StorageClass): OK
         * 201 (StorageClass): Created
         * 401: Unauthorized
         */
        public static final String UPDATE_STORAGE_CLASS = "/apis/storage.k8s.io/v1/storageclasses/{name}";
        /**
         * patch 部分更新指定的 StorageClass
         * HTTP 请求
         * PATCH /apis/storage.k8s.io/v1/storageclasses/{name}
         * 参数
         * name (路径参数): string，必需 StorageClass 的名称。
         * body: Patch，必需
         * dryRun (查询参数): string dryRun
         * fieldManager (查询参数): string fieldManager
         * fieldValidation (查询参数): string fieldValidation
         * force (查询参数): boolean force
         * pretty (查询参数): string pretty
         * 响应
         * 200 (StorageClass): OK
         * 201 (StorageClass): Created
         * 401: Unauthorized
         */
        public static final String PATCH_STORAGE_CLASS = "/apis/storage.k8s.io/v1/storageclasses/{name}";
        /**
         * delete 删除 StorageClass
         * HTTP 请求
         * DELETE /apis/storage.k8s.io/v1/storageclasses/{name}
         * 参数
         * name (路径参数): string，必需 StorageClass 的名称。
         * body: DeleteOptions
         * dryRun (查询参数): string dryRun
         * gracePeriodSeconds (查询参数): integer gracePeriodSeconds
         * ignoreStoreReadErrorWithClusterBreakingPotential (查询参数): boolean ignoreStoreReadErrorWithClusterBreakingPotential
         * pretty (查询参数): string pretty
         * propagationPolicy (查询参数): string propagationPolicy
         * 响应
         * 200 (StorageClass): OK
         * 202 (StorageClass): Accepted
         * 401: Unauthorized
         */
        public static final String DELETE_STORAGE_CLASS = "/apis/storage.k8s.io/v1/storageclasses/{name}";
    }
}

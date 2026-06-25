
#搜索仓库
helm search repo redis

#显示配置的说明
helm show readme bitnami/redis

#拉取到本地,https://artifacthub.io/ 所有chart仓库地址,可以在这看到所有的版本，后面可以指定CHART VERSION版本号
helm pull bitnami/redis --version 25.3.11

# 将redis解压
tar -xf redis-25.3.11.tgz  --strip-components=1

# 修改values.yaml
vim values.yaml
# 1.修改global.storageClass为local-path
# 2.修改global.redis.password为wolfcode123
# 3.修改master.persistence.size为1Gi
# 4.修改master.service.type为NodePort
# 5.修改replica.persistence.size为1Gi
# 6.修改replica.service.type为NodePort
# 7.修改sentinel.persistence.size为100Mi
# 8.修改sentinel.service.type为NodePort

# 删除多余的文件
rm -f redis.sh
rm -f redis-25.3.11.tgz

# 创建redis命名空间
kubectl create namespace redis
# 安装redis
helm install redis . -n redis
# 查看redis
kubectl get deploy,pod,svc,pvc -n redis -o wide
# 查看pod
kubectl describe pod/redis-master-0 -n redis
# NAME                   READY   STATUS              RESTARTS   AGE     IP              NODE        NOMINATED NODE   READINESS GATES
#pod/redis-master-0     1/1     Running             0          4m12s   10.233.110.69   worker1     <none>           <none>
#pod/redis-replicas-0   1/1     Running             0          4m12s   10.233.110.70   worker1     <none>           <none>
#pod/redis-replicas-1   0/1     ContainerCreating   0          57s     <none>          worker003   <none>           <none>
#
#NAME                     TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)          AGE     SELECTOR
#service/redis-headless   ClusterIP   None           <none>        6379/TCP         4m12s   app.kubernetes.io/instance=redis,app.kubernetes.io/name=redis
#service/redis-master     NodePort    10.233.38.82   <none>        6379:32652/TCP   4m12s   app.kubernetes.io/component=master,app.kubernetes.io/instance=redis,app.kubernetes.io/name=redis
#service/redis-replicas   NodePort    10.233.22.69   <none>        6379:30702/TCP   4m12s   app.kubernetes.io/component=replica,app.kubernetes.io/instance=redis,app.kubernetes.io/name=redis
#
#NAME                                                STATUS   VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS   VOLUMEATTRIBUTESCLASS   AGE     VOLUMEMODE
#persistentvolumeclaim/redis-data-redis-master-0     Bound    pvc-1f84dd0f-a58d-4389-9c5c-c06f2414b29f   1Gi        RWO            local-path     <unset>                 4m12s   Filesystem
#persistentvolumeclaim/redis-data-redis-replicas-0   Bound    pvc-1400cb4c-4162-4461-beb5-dafd51588c2e   1Gi        RWO            local-path     <unset>                 4m12s   Filesystem
#persistentvolumeclaim/redis-data-redis-replicas-1   Bound    pvc-0fe684a5-681b-4f30-9d06-f85c612a8623   1Gi        RWO            local-path     <unset>                 57s     Filesystem

# 进入 pod
kubectl exec -it redis-replicas-0 -n redis -- bash
redis-cli
127.0.0.1:6379> auth wolfcode123
#OK
127.0.0.1:6379> get test
#"wolfcode"
127.0.0.1:6379> set test "xxx"
#(error) READONLY You can't write against a read only replica.

# 通过服务名来访问
redis-master.redis:6379
redis-replicas.redis:6379

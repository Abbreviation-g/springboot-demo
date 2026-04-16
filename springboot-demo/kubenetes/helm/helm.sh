helm search hub redis
helm list -n ingress-nginx

# 添加helm仓库
#helm repo add azure http://mirror.azure.cn/kubernetes/charts
#helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo add volcano-sh      https://volcano-sh.github.io/helm-charts
helm repo add koordinator-sh  https://koordinator-sh.github.io/charts/
helm search repo redis

helm pull bitnami/redis
tar -xf redis-25.3.11.tgz

# 修改values.yaml
# 修改 storageClass 为 managed-nfs-storage
# 设置 redis 密码 password

# 安装操作
# 创建命名空间
kubectl create namespace redis
# 安装
cd ../
helm install redis ./redis -n redis

kubectl get all -n redis
#NAME                   READY   STATUS              RESTARTS   AGE
#pod/redis-master-0     0/1     ContainerCreating   0          2m20s
#pod/redis-replicas-0   0/1     ContainerCreating   0          2m20s
#
#NAME                     TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
#service/redis-headless   ClusterIP   None            <none>        6379/TCP   2m20s
#service/redis-master     ClusterIP   10.233.7.235    <none>        6379/TCP   2m20s
#service/redis-replicas   ClusterIP   10.233.62.100   <none>        6379/TCP   2m20s
#
#NAME                              READY   AGE
#statefulset.apps/redis-master     0/1     2m20s
#statefulset.apps/redis-replicas   0/3     2m20s
kubectl get pvc -n redis
#NAME                          STATUS   VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS          VOLUMEATTRIBUTESCLASS   AGE
#redis-data-redis-master-0     Bound    pvc-36b84b1f-e039-4f7d-8e20-c39a9ce44068   1Gi        RWO            managed-nfs-storage   <unset>                 3m18s
#redis-data-redis-replicas-0   Bound    pvc-f618fbb6-4b14-45a0-ba78-c1c25384ae95   8Gi        RWO            managed-nfs-storage   <unset>                 3m18s

kubectl exec -it redis-master-0 -n redis -- /bin/bash
#I have no name! [ / ]$ redis-cli
#127.0.0.1:6379> auth password
#OK
#127.0.0.1:6379> set name xiaoliu
#OK
#127.0.0.1:6379> get name
#"xiaoliu"
#127.0.0.1:6379> exit
#I have no name! [ / ]$ exit
#exit
kubectl exec -it redis-replicas-1 -n redis -- /bin/bash
#I have no name! [ / ]$ redis-cli
#127.0.0.1:6379> auth password
#OK
#127.0.0.1:6379> set name xx
#(error) READONLY You can't write against a read only replica.
#127.0.0.1:6379> get name
#"xiaoliu"
#127.0.0.1:6379> exit
#I have no name! [ / ]$ exit
#exit

# 卸载
helm uninstall redis -n redis

#--------------------------------------------------------------------------------------------------------

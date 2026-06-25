helm repo add rocketmq-repo https://helm-charts.itboon.top/rocketmq
#"rocketmq-repo" has been added to your repositories
helm repo update rocketmq-repo
#Hang tight while we grab the latest from your chart repositories...
#...Successfully got an update from the "rocketmq-repo" chart repository
#Update Complete. ⎈Happy Helming!⎈
helm search repo rocketmq
#NAME                            CHART VERSION   APP VERSION     DESCRIPTION
#rocketmq-repo/rocketmq          12.6.0          5.4.0           RocketMQ Helm chart
#rocketmq-repo/rocketmq-cluster  12.6.0          5.4.0           RocketMQ Helm chart

helm pull rocketmq-repo/rocketmq-cluster
tar -zxf rocketmq-cluster-12.6.0.tgz --strip-components=1
rm rocketmq-cluster-12.6.0.tgz
rm rocketmq.sh

# 修改values.yaml
# 修改
#broker:
#  size:
#    master: 1
#    replica: 1
# 修改
#broker:
#  master:
#    brokerRole: ASYNC_MASTER
#    jvm:
#      maxHeapSize: 2048M
#      # javaOptsOverride: ""
#    resources:
#      limits:
#        cpu: 1
#        memory: 1Gi
#      requests:
#        cpu: 100m
#        memory: 1Gi
#  replica:
#    jvm:
#      maxHeapSize: 1300M
#      # javaOptsOverride: ""
#    resources:
#      limits:
#        cpu: 1
#        memory: 1Gi
#      requests:
#        cpu: 50m
#        memory: 1Gi
# 修改
#nameserver:
#  replicaCount: 1
# 修改
#dashboard
#  service:
#    annotations: {}
#    type: NodePort
#    nodePort: 31007
# 修改
#nameserver:
#  service:
#    annotations: {}
#    type: NodePort
#    nodePort: 31008
# 修改
#nameserver:
#  persistence:
#    enabled: false
#    size: 2Gi
#    storageClass: "local-path"
#修改
#broker:
#  persistence:
#    enabled: true
#    size: 2Gi
#    storageClass: "local-path"
#修改
#proxy:
#  enabled: false


#1、创建namespace
root@master001:~/temp/devops-microservices/microservices/basic/rocketmq# kubectl create ns rocketmq
#namespace/rocketmq created
root@master001:~/temp/devops-microservices/microservices/basic/rocketmq# helm install rocketmq . -n rocketmq
#NAME: rocketmq
#LAST DEPLOYED: Thu Jun 25 03:12:21 2026
#NAMESPACE: rocketmq
#STATUS: deployed
#REVISION: 1
#TEST SUITE: None
#NOTES:
#>>> Nameserver Address:
#    rocketmq-nameserver.rocketmq.svc:9876
#
#>>> Proxy Remoting Address:
#    rocketmq-proxy.rocketmq.svc:8080
#
#>>> Proxy gRPC Address:
#    rocketmq-proxy.rocketmq.svc:8081
#
#>>> RocketMQ Dashboard Auth:
#      username: admin   password: admin
#      username: user01  password: userPass
#    Modify "rocketmq-dashboard-cm" configmap to change the password
root@master001:~/temp/devops-microservices/microservices/basic/rocketmq# kubectl get deploy,pod,svc,pvc -n rocketmq -o wide
#NAME                                 READY   UP-TO-DATE   AVAILABLE   AGE   CONTAINERS   IMAGES                                    SELECTOR
#deployment.apps/rocketmq-dashboard   0/1     1            0           73s   dashboard    apacherocketmq/rocketmq-dashboard:2.1.0   app.kubernetes.io/instance=rocketmq,app.kubernetes.io/name=rocketmq,component=dashboard
#deployment.apps/rocketmq-proxy       0/2     2            0           73s   proxy        apache/rocketmq:5.4.0                     app.kubernetes.io/instance=rocketmq,app.kubernetes.io/name=rocketmq,component=proxy
#
#NAME                                     READY   STATUS              RESTARTS   AGE   IP       NODE        NOMINATED NODE   READINESS GATES
#pod/rocketmq-broker-master-0             0/1     Pending             0          72s   <none>   <none>      <none>           <none>
#pod/rocketmq-broker-replica-id1-0        0/1     Pending             0          72s   <none>   <none>      <none>           <none>
#pod/rocketmq-dashboard-54f5866b7-8z66k   0/1     ContainerCreating   0          73s   <none>   worker1     <none>           <none>
#pod/rocketmq-nameserver-0                0/1     ContainerCreating   0          73s   <none>   worker1     <none>           <none>
#pod/rocketmq-proxy-669844fd6c-m8q8r      0/1     ContainerCreating   0          72s   <none>   worker003   <none>           <none>
#pod/rocketmq-proxy-669844fd6c-z4kx6      0/1     ContainerCreating   0          73s   <none>   worker1     <none>           <none>
#
#NAME                                   TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)             AGE   SELECTOR
#service/rocketmq-dashboard             NodePort    10.233.9.223    <none>        8082:31007/TCP      73s   app.kubernetes.io/instance=rocketmq,app.kubernetes.io/name=rocketmq,component=dashboard
#service/rocketmq-nameserver            ClusterIP   10.233.24.57    <none>        9876/TCP            73s   app.kubernetes.io/instance=rocketmq,app.kubernetes.io/name=rocketmq,component=nameserver
#service/rocketmq-nameserver-headless   ClusterIP   None            <none>        9876/TCP            73s   app.kubernetes.io/instance=rocketmq,app.kubernetes.io/name=rocketmq,component=nameserver
#service/rocketmq-proxy                 ClusterIP   10.233.19.143   <none>        8080/TCP,8081/TCP   73s   app.kubernetes.io/instance=rocketmq,app.kubernetes.io/name=rocketmq,component=proxy
#
#NAME                                                                 STATUS    VOLUME   CAPACITY   ACCESS MODES   STORAGECLASS   VOLUMEATTRIBUTESCLASS   AGE   VOLUMEMODE
#persistentvolumeclaim/broker-storage-rocketmq-broker-master-0        Pending                                                     <unset>                 72s   Filesystem
#persistentvolumeclaim/broker-storage-rocketmq-broker-replica-id1-0   Pending                                                     <unset>                 72s   Filesystem



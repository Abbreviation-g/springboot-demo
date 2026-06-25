kubectl apply -f seata-namespace.yaml
kubectl apply -f seata-server.yaml
kubectl get deploy,pod,svc -n seata
#NAME                           READY   UP-TO-DATE   AVAILABLE   AGE
#deployment.apps/seata-server   0/3     3            0           119s
#
#NAME                                READY   STATUS              RESTARTS   AGE
#pod/seata-server-6ccc5b5464-66mm9   0/1     ContainerCreating   0          119s
#pod/seata-server-6ccc5b5464-7dm2c   0/1     ContainerCreating   0          119s
#pod/seata-server-6ccc5b5464-n8bs6   0/1     ContainerCreating   0          119s
#
#NAME                     TYPE       CLUSTER-IP    EXTERNAL-IP   PORT(S)                         AGE
#service/seata-headless   NodePort   10.233.16.3   <none>        8091:30959/TCP,7091:31031/TCP   119s

# 访问地址
# seata-headless.seata:8091
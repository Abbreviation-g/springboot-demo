#----------------------------------------------------------------------------------------------------------------------
kubectl get endpoints -n namespace-0327
kubectl get ep -n namespace-0327
kubectl get po -n namespace-0327 -l app=nginx-deploy -o wide

#----------------------------------------------------------------------------------------------------------------------
kubectl run -it --image busybox:1.28.4 dns-test /bin/sh -n namespace-0327
kubectl exec -it dns-test -n namespace-0327 -- sh
# 默认在当前 namespace 中访问，如果需要跨 namespace 访问 pod，则在 service name 后面加上 .<namespace> 即可
wget http://nginx-svc
wget http://nginx-svc.namespace-0327

#----------------------------------------------------------------------------------------------------------------------
kubectl exec -it dns-test -n namespace-0327 -- sh
wget http://nginx-svc-external

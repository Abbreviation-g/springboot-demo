# ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# DNS-TEST
#kubectl run -it --image busybox dns-test --restart=Never --rm /bin/sh -n namespace-0327
kubectl run -it --image busybox:1.28.4 dns-test /bin/sh -n namespace-0327
# ping web-0.nginx
# ping web-1.nginx
# nslookup web-0.nginx
##
##Server:    169.254.25.10
##Address 1: 169.254.25.10
##
##Name:      web-0.nginx
##Address 1: 10.233.109.104 web-0.nginx.namespace-0327.svc.cluster.local

# ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# 扩容缩容
kubectl scale sts web --replicas=5 -n namespace-0327
kubectl scale sts web --replicas=2 -n namespace-0327
kubectl describe sts web -n namespace-0327

# ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# 镜像更新
kubectl patch sts web --type='json' -p='[{"op": "replace", "path": "/spec/template/spec/containers/0/image", "value":"nginx:1.9.1"}]' -n namespace-0327
kubectl rollout history sts web -n namespace-0327
kubectl rollout history sts web -n namespace-0327 --revision=2
kubectl rollout status sts web -n namespace-0327
# 灰度发布 partition
# 先扩容
kubectl scale sts web --replicas=5 -n namespace-0327
kubectl edit sts web -n namespace-0327
# 修改spec.template.spec.containers[0].image: nginx:1.7.9
# 修改spec.updateStrategy.rollingUpdate.partition: 3

# ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# 修改更新策略为 OnDelete
kubectl edit sts web -n namespace-0327
# 先删除spec.updateStrategy
# 再修改spec.updateStrategy.type: OnDelete
# 保存并退出
# 再次修改镜像版本为 nginx:1.9.1
# 保存并退出
kubectl describe po web-0 -n namespace-0327
# 一个一个删除pod，再查看pod的状态
kubectl delete po web-0 -n namespace-0327
kubectl describe po web-0 -n namespace-0327
kubectl delete po web-1 -n namespace-0327
kubectl describe po web-1 -n namespace-0327
kubectl delete po web-2 -n namespace-0327
kubectl describe po web-2 -n namespace-0327

# ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# 删除statefulset
# 1.级联删除：删除 statefulset 时会同时删除 pods
kubectl delete statefulset web -n namespace-0327
kubectl delete svc nginx -n namespace-0327
# 2. 非级联删除：删除 statefulset 时不会删除 pods，删除 sts 后，pods 就没人管了，此时再删除 pod 不会重建的
kubectl delete sts web --cascade=false -n namespace-0327
kubectl get sts -n namespace-0327 # sts已经不在了
kubectl get po -n namespace-0327 # pod还在
kubectl delete po web-0 -n namespace-0327 # 手动一个一个删除pod
kubectl delete po web-1 -n namespace-0327 # 手动一个一个删除pod
kubectl delete svc nginx -n namespace-0327


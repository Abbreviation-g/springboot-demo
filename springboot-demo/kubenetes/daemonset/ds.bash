# ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# 1. nodeSelector
# 往worker1节点添加标签
kubectl label node worker1 type=microservices
# 验证
kubectl get node worker1 --show-labels

# 修改fulentd这个daemonset的选择器
kubectl edit ds fluentd -n namespace-0327
#spec:
#  template:
#    spec:
#      nodeSelector:
#        type: microservices
# 保存并退出

# 可用看到ds只剩下一个了
kubectl get ds -n namespace-0327
# 可以看到pod也只剩下一个了，并且被部署到了worker1节点
kubectl get po -n namespace-0327 -o wide

# 给worker002添加标签
kubectl label node worker002 type=microservices
# 可用看到ds变成了两个可用了
kubectl get ds -n namespace-0327
# 可以看到pod也变成两个了，并且被部署到了worker1 worker002节点
kubectl get po -n namespace-0327 -o wide

# 最后再往worker1节点删除标签
kubectl label node worker1 type-
kubectl label node worker002 type-

# ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# 不建议使用 RollingUpdate，建议使用 OnDelete 模式，这样避免频繁更新 ds
kubectl edit ds fluentd -n namespace-0327
# spec.updateStrategy.type: OnDelete

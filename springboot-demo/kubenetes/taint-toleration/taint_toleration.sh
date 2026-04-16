# 查看污点
#kubectl describe no k8s-master
# 为节点打上污点
#kubectl taint node k8s-master key=value:NoSchedule
# 移除污点
#kubectl taint node k8s-master key=value:NoSchedule-

kubectl get node -o wide
kubectl label node worker001 nfsclient=yes
kubectl label node worker002 nfsserver=yes
kubectl label node worker002 nfs=yes
kubectl label node worker001 nfs=yes

kubectl taint no worker001 memory=low:NoSchedule
kubectl taint no worker002 memory=low:NoSchedule
kubectl taint no worker003 memory=low:NoSchedule
kubectl taint no worker1 memory=low:NoSchedule

kubectl taint no worker001 memory=low:NoSchedule-
kubectl taint no worker002 memory=low:NoSchedule-
kubectl taint no worker003 memory=low:NoSchedule-
kubectl taint no worker1 memory=low:NoSchedule-

kubectl taint no worker001 memory=low:NoExecute
kubectl taint no worker002 memory=low:NoExecute
kubectl taint no worker003 memory=low:NoExecute
kubectl taint no worker1 memory=low:NoExecute

kubectl taint no worker001 memory=low:NoExecute-
kubectl taint no worker002 memory=low:NoExecute-
kubectl taint no worker003 memory=low:NoExecute-
kubectl taint no worker1 memory=low:NoExecute-

kubectl taint node master001 node-role.kubernetes.io/control-plane:NoSchedule-
kubectl taint node master001 node-role.kubernetes.io/control-plane:NoSchedule
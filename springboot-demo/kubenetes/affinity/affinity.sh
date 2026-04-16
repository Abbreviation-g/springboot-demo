kubectl label node worker001 nfsclient=yes
kubectl label node worker002 nfsserver=yes
kubectl label node worker002 nfs=yes
kubectl label node worker001 nfs=yes

kubectl label node worker001 worker002 topology.kubernetes.io/zone=V --overwrite
kubectl label node master001 topology.kubernetes.io/zone=R --overwrite

kubectl get node --show-labels

kubectl get pod nginx-deploy-s2-bf878d564-5qltj -n namespace-0327 -o wide --show-labels
#NAME                               READY   STATUS      RESTARTS   AGE     IP               NODE        NOMINATED NODE   READINESS GATES   LABELS
#nginx-deploy-s1-7999b5b5f6-cthj8   1/1     Running     0          63s     10.233.110.137   worker1     <none>           <none>            app=nginx-deploy-s1,pod-template-hash=7999b5b5f6,security=S1,topology.kubernetes.io/zone=V
#nginx-deploy-s1-7999b5b5f6-lqrqf   1/1     Running     0          63s     10.233.109.247   worker003   <none>           <none>            app=nginx-deploy-s1,pod-template-hash=7999b5b5f6,security=S1,topology.kubernetes.io/zone=V
#nginx-deploy-s1-7999b5b5f6-nms9k   1/1     Running     0          63s     10.233.109.248   worker003   <none>           <none>            app=nginx-deploy-s1,pod-template-hash=7999b5b5f6,security=S1,topology.kubernetes.io/zone=V
#nginx-deploy-s1-7999b5b5f6-pb4j6   1/1     Running     0          63s     10.233.70.148    bjsw-s02    <none>           <none>            app=nginx-deploy-s1,pod-template-hash=7999b5b5f6,security=S1,topology.kubernetes.io/zone=V
#nginx-deploy-s1-7999b5b5f6-ppsvc   1/1     Running     0          63s     10.233.110.131   worker1     <none>           <none>            app=nginx-deploy-s1,pod-template-hash=7999b5b5f6,security=S1,topology.kubernetes.io/zone=V
#nginx-deploy-s2-bf878d564-5qltj    1/1     Running     0          4m32s   10.233.77.168    master001   <none>           <none>            app=nginx-deploy-s2,pod-template-hash=bf878d564,security=S2,topology.kubernetes.io/zone=V

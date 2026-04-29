#1. 安装 local-path-provisioner
#首先，如果您还没有安装 local-path-provisioner，需要先部署它：
#这将创建一个名为 local-path 的 StorageClass。
kubectl apply -f local-path-storage.yaml
#namespace/local-path-storage created
#serviceaccount/local-path-provisioner-service-account created
#clusterrole.rbac.authorization.k8s.io/local-path-provisioner-role created
#clusterrolebinding.rbac.authorization.k8s.io/local-path-provisioner-bind created
#deployment.apps/local-path-provisioner created
#storageclass.storage.k8s.io/local-path created
#configmap/local-path-config created

#2. 使用 local-path-provisioner 自动创建 PV
#最简单的方式是创建一个 PVC，让 local-path-provisioner 自动创建 PV：
kubectl apply -f test-local-path-pod.yaml
#persistentvolumeclaim/local-path-pvc created
#pod/test-local-path-pod created
kubectl get pvc local-path-pvc -n namespace-0327 -o yaml
#apiVersion: v1
#kind: PersistentVolumeClaim
#metadata:
#  annotations:
#    kubectl.kubernetes.io/last-applied-configuration: |
#      {"apiVersion":"v1","kind":"PersistentVolumeClaim","metadata":{"annotations":{},"name":"local-path-pvc","namespace":"namespace-0327"},"spec":{"accessModes":["ReadWriteOnce"],"resources":{"requests":{"storage":"1Gi"}},"storageClassName":"local-path"}}
#    pv.kubernetes.io/bind-completed: "yes"
#    pv.kubernetes.io/bound-by-controller: "yes"
#    volume.beta.kubernetes.io/storage-provisioner: rancher.io/local-path
#    volume.kubernetes.io/selected-node: worker1
#    volume.kubernetes.io/storage-provisioner: rancher.io/local-path
#  creationTimestamp: "2026-04-29T09:00:40Z"
#  finalizers:
#  - kubernetes.io/pvc-protection
#  name: local-path-pvc
#  namespace: namespace-0327
#  resourceVersion: "61265639"
#  uid: 0861d4a1-45eb-410e-8306-28db90adca99
#spec:
#  accessModes:
#  - ReadWriteOnce
#  resources:
#    requests:
#      storage: 1Gi
#  storageClassName: local-path
#  volumeMode: Filesystem
#  volumeName: pvc-0861d4a1-45eb-410e-8306-28db90adca99
#status:
#  accessModes:
#  - ReadWriteOnce
#  capacity:
#    storage: 1Gi
#  phase: Bound
kubectl describe pv pvc-0861d4a1-45eb-410e-8306-28db90adca99
#Name:              pvc-0861d4a1-45eb-410e-8306-28db90adca99
#Labels:            <none>
#Annotations:       pv.kubernetes.io/provisioned-by: rancher.io/local-path
#Finalizers:        [kubernetes.io/pv-protection]
#StorageClass:      local-path
#Status:            Bound
#Claim:             namespace-0327/local-path-pvc
#Reclaim Policy:    Delete
#Access Modes:      RWO
#VolumeMode:        Filesystem
#Capacity:          1Gi
#Node Affinity:
#  Required Terms:
#    Term 0:        kubernetes.io/hostname in [worker1]
#Message:
#Source:
#    Type:          HostPath (bare host directory volume)
#    Path:          /opt/local-path-provisioner/pvc-0861d4a1-45eb-410e-8306-28db90adca99_namespace-0327_local-path-pvc
#    HostPathType:  DirectoryOrCreate
#Events:            <none>


#3. 或者手动创建 PersistentVolume
#如果您需要手动创建 PV，可以使用以下 YAML：
kubectl apply -f manual-local-path-pvc.yaml
kubectl describe pv local-path-pv
#Name:              local-path-pv
#Labels:            <none>
#Annotations:       pv.kubernetes.io/bound-by-controller: yes
#Finalizers:        [kubernetes.io/pv-protection]
#StorageClass:      local-path
#Status:            Bound
#Claim:             namespace-0327/manual-local-path-pvc
#Reclaim Policy:    Retain
#Access Modes:      RWO
#VolumeMode:        Filesystem
#Capacity:          1Gi
#Node Affinity:
#  Required Terms:
#    Term 0:        kubernetes.io/hostname in [master001]
#Message:
#Source:
#    Type:  LocalVolume (a persistent volume backed by local storage on a node)
#    Path:  /opt/local-path-provisioner/pv-data
#Events:    <none>
kubectl describe persistentvolumeclaim/manual-local-path-pvc -n namespace-0327
#Name:          manual-local-path-pvc
#Namespace:     namespace-0327
#StorageClass:  local-path
#Status:        Bound
#Volume:        local-path-pv
#Labels:        <none>
#Annotations:   pv.kubernetes.io/bind-completed: yes
#Finalizers:    [kubernetes.io/pvc-protection]
#Capacity:      1Gi
#Access Modes:  RWO
#VolumeMode:    Filesystem
#Used By:       <none>
#Events:        <none>


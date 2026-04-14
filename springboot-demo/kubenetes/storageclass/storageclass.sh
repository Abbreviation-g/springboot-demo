kubectl apply -f nfs-provisioner-rbac.yaml
kubectl apply -f nfs-provisioner-deployment.yaml
kubectl apply -f nfs-storage-class.yaml
kubectl apply -f nfs-sc-demo-statefulset.yaml
kubectl apply -f auto-pv-test-pvc.yaml

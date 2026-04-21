wget https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml

# 修改属性
#kind: Service
#apiVersion: v1
#metadata:
#  labels:
#    k8s-app: kubernetes-dashboard
#  name: kubernetes-dashboard
#  namespace: kubernetes-dashboard
#spec:
#  type: NodePort   #新增
#  ports:
#    - port: 443
#      targetPort: 8443
#  selector:
#    k8s-app: kubernetes-dashboard

kubectl apply -f recommended.yaml
kubectl get all -n kubernetes-dashboard -o wide
kubectl get svc -n kubernetes-dashboard -o wide
# https://172.16.31.35:30791

kubectl  apply -f dashboard-admin.yaml
#kubectl create token dashboard-admin -n kubernetes-dashboard
# eyJhbGciOiJSUzI1NiIsImtpZCI6InFtWmFudFZvVzdvZTBrVlhaZWJpbWxXQ0I4ZWpVOWVNRGtjYkRzVFVrZFUifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjoxNzc2NDE4ODEwLCJpYXQiOjE3NzY0MTUyMTAsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwianRpIjoiMDk3ZTZiMDMtZmFiMS00MzhlLTljZDItMGM1OGJiZDMxNGI2Iiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsInNlcnZpY2VhY2NvdW50Ijp7Im5hbWUiOiJkYXNoYm9hcmQtYWRtaW4iLCJ1aWQiOiIxZjg2MDA2Ny1iZTI1LTQ5NDMtYTliMC03Y2E0Y2JjNzE1MTMifX0sIm5iZiI6MTc3NjQxNTIxMCwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmVybmV0ZXMtZGFzaGJvYXJkOmRhc2hib2FyZC1hZG1pbiJ9.rf5LXBIgKk6B3rsfH3R6ehBiqQMuNeJlf6ICbIvYjRlTODlAZ9U2zvgREOGi9-3QGsd0DrnnUlDDLvlsBwgGv-D9lie5ELJ55KnPWr54emcWU0VVMn88j_iIY8hNaXJA3iQl-OlE2Uyi601AioRxSvZfgQdRNHgCyniTxLkkaPwSkouPUug66FceRMCTplSMcgSqD3JSu-opZKfLQSxqUJSXCYyF9H4DhWRzkCwJq9ddKM7kS9QXBaimvRqnYlQK5M8uPHqInDMlA3K1wAegCRIfml5GmZDIxxQITJD4DSn63NddON9ei61584ghyrNQzgsyzjESS-Si2Lh7ZvEQLQ
#kubectl describe serviceaccount dashboard-admin -n kubernetes-dashboard
kubectl describe secret dashboard-admin-token -n kubernetes-dashboard


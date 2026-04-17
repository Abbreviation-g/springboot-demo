kubectl label node worker002 es=data

kubectl apply -f es.yaml
kubectl apply -f kube-logging-ns.yaml
kubectl apply -f logstash.yaml
kubectl apply -f filebeat.yaml
kubectl apply -f kibana.yaml

kubectl get po -n kube-logging
kubectl get svc -n kube-logging
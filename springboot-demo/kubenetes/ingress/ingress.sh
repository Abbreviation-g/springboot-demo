helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo list
helm search repo ingress-nginx
helm pull ingress-nginx/ingress-nginx
tar -xf ingress-nginx-4.15.1.tgz
vim values.yaml

kubectl create ns ingress-nginx
kubectl label node k8s-node1 ingress=true

kubectl create -f wolfcode-ingress.yaml
kubectl get ingress -n namespace-0327

netstat -ntlp


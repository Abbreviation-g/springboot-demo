# 1. 先准备一个好一个有做资源限制的 deployment
kubectl apply -f nginx-deploy.yaml
kubectl replace -f nginx-deploy.yaml

# 2. 创建一个 autoscaler
kubectl autoscale deploy nginx-deploy --cpu-percent=20 --min=2 --max=5 -n namespace-0327

# 3. 查看 deployment
kubectl get deploy -n namespace-0327
# NAME           READY   UP-TO-DATE   AVAILABLE   AGE
# nginx-deploy   2/2     2            2           7m36s

# 安装组件
kubectl apply -f metrics-server-components.yaml

# 查看 pod 状态
kubectl get pods --all-namespaces | grep metrics

# top命令查看pod状态
kubectl get pod -n namespace-0327
kubectl top pod nginx-deploy-7996bc758d-kv29k -n namespace-0327

# 增加nginx-deploy的并发压力
kubectl apply nginx-svc.yaml
kubectl get svc nginx-svc -n namespace-0327 #获取到端口号31465
kubectl get node -o wide # 获取到master节点的ip
curl 172.16.31.35:31465 # 访问成功
# while true; do wget -q -O- http://<ip:port> > /dev/null ; done
while true; do wget -q -O- http://172.16.31.35:31465 > /dev/null ; done

# 查看hpa
kubectl get hpa -n namespace-0327
kubectl edit deploy nginx-deploy -n namespace-0327
# 将requests.cpu改成4m

# 查看pod状态
kubectl get hpa -n namespace-0327
kubectl top po -n namespace-0327
kubectl describe hpa nginx-deploy -n namespace-0327

# 停止并发，过十分钟再次查看状态

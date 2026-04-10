#--------------------------------------------------------------------------------------------------------------------
vim db.properties
# username=root
# password=admin
vim redis.properties
# host=127.0.0.1
# port=6379

kubectl create cm -h

kubectl create configmap test-dir-config --from-file=./ -n namespace-0327

#--------------------------------------------------------------------------------------------------------------------
vim application.yaml
#spring:
#  application:
#    name: app
#server:
#  port: 8080
kubectl create cm spring-boot-test-yaml --from-file=application.yaml -n namespace-0327

#--------------------------------------------------------------------------------------------------------------------
kubectl create cm spring-boot-test-alises-yaml --from-file=app.yaml=application.yaml -n namespace-0327

#--------------------------------------------------------------------------------------------------------------------
kubectl create cm test-key-value-config --from-literal=username=root --from-literal=password=admin -n namespace-0327
kubectl describe cm test-key-value-config -n namespace-0327

# 把configmap加载到容器里面打印出来
#--------------------------------------------------------------------------------------------------------------------
kubectl create cm test-env-config --from-literal=JAVA_OPS_TEST='-Xms512m -Xmx512m' --from-literal=APP_NAME='SpringBootEnv' -n namespace-0327
kubectl describe cm test-env-config -n namespace-0327
kubectl create -f test-env-pod.yaml
kubectl logs -f test-env-pod -n namespace-0327

#--------------------------------------------------------------------------------------------------------------------
kubectl create -f test-configfile-pod.yaml
kubectl exec -it test-configfile-pod -n namespace-0327 -- sh
#/ # cd /usr/local/mysql/conf
#/usr/local/mysql/conf # ls
#db.properties
#/usr/local/mysql/conf # cat db.properties
#username=root
#password=admin
#/usr/local/mysql/conf # exit


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


# SUBPATH解决加载配置覆盖的问题
#------------------------------------------------------------------------------------------------------------------
kubectl exec -it nginx-deploy-569b488b94-qtk48 -n namespace-0327 -- sh
cat /etc/nginx/nginx.conf
exit
vim nginx.conf
kubectl create cm nginx-conf-cm --from-file=nginx.conf -n namespace-0327
kubectl describe cm nginx-conf-cm -n namespace-0327
kubectl edit deploy nginx-deploy -n namespace-0327

#apiVersion: apps/v1
#kind: Deployment
#metadata:
#  annotations:
#    deployment.kubernetes.io/revision: "4"
#  creationTimestamp: "2026-04-08T09:07:48Z"
#  generation: 8
#  labels:
#    app: nginx-deploy
#  name: nginx-deploy
#  namespace: namespace-0327
#  resourceVersion: "40015436"
#  uid: 3058d663-b7d1-4d46-9e80-1c5bc7860d52
#spec:
#  progressDeadlineSeconds: 600
#  replicas: 2
#  revisionHistoryLimit: 10
#  selector:
#    matchLabels:
#      app: nginx-deploy
#  strategy:
#    rollingUpdate:
#      maxSurge: 25%
#      maxUnavailable: 25%
#    type: RollingUpdate
#  template:
#    metadata:
#      creationTimestamp: null
#      labels:
#        app: nginx-deploy
#    spec:
#      containers:
#      - image: nginx:1.9.1
#        imagePullPolicy: IfNotPresent
#        name: nginx
#        command: ['/bin/sh', '-c', 'nginx daemon off; sleep 3600']
#        resources:
#          limits:
#            cpu: 200m
#            memory: 128Mi
#          requests:
#            cpu: 4m
#            memory: 128Mi
#        terminationMessagePath: /dev/termination-log
#        terminationMessagePolicy: File
#        volumeMounts: # 挂载数据卷
#        - name: nginx-conf # 数据卷名称
#          mountPath: '/etc/nginx' # 挂载的路径
#      volumes: # 数据卷定义
#      - name: nginx-conf # 数据卷名称
#        configMap:  # 数据卷的类型为configmap
#        name: nginx-conf-cm # configmap的名称
#          items:  # 将configmap中哪些数据挂载进来
#          - key: nginx.conf # 指定挂载哪一个key
#            path: nginx.conf # 挂载后该key重命名为什么名字
#      dnsPolicy: ClusterFirst
#      restartPolicy: Always
#      schedulerName: default-scheduler
#      securityContext: {}
#      terminationGracePeriodSeconds: 30

kubectl get po nginx-deploy-569b488b94-qtk48 -n namespace-0327 -o yaml

kubectl exec -it nginx-deploy-678b6bd9f-jwxgh -n namespace-0327 -- sh
# ls /etc/nginx
# 发现这个目录下的文件只剩下nginx.conf, 因此需要永subPath解决
#          volumeMounts: # 挂载数据卷
#            - name: nginx-conf # 数据卷名称
#              mountPath: '/etc/nginx/nginx.conf' # 挂载的路径
#              subPath: etc/nginx/nginx.conf
#      volumes: # 数据卷定义
#        - name: nginx-conf # 数据卷名称
#          configMap: # 数据卷的类型为configmap
#            name: nginx-conf-cm # configmap的名称
#            items: # 将configmap中哪些数据挂载进来
#              - key: nginx.conf # 指定挂载哪一个key
#                path: etc/nginx/nginx.conf # 挂载后该key重命名为什么名字

#---------------------------------------------------------------------------------------------------------------------
# configmap热更新
#对于 subPath 的方式，我们可以取消 subPath 的使用，将配置文件挂载到一个不存在的目录，避免目录的覆盖，然后再利用软连接的形式，将该文件链接到目标位置
#但是如果目标位置原本就有文件，可能无法创建软链接，此时可以基于前面讲过的 postStart 操作执行删除命令，将默认的吻技安删除即可
# 2
kubectl exec -it private-image-pull-pod -n namespace-0327 -- sh
cat /usr/local/mysql/conf/db.properties
# 同时在另一个终端修改cm
kubectl edit cm test-dir-config -n namespace-0327
#再次cat /usr/local/mysql/conf/db.properties

# 3. replace
kubectl create cm test-dir-config --from-file=./conf/ --dry-run -n namespace-0327 -o yaml | kubectl replace -f-
kubectl exec -it private-image-pull-pod -n namespace-0327 -- sh
cat /usr/local/mysql/conf/db.properties

# 4. 不可变的secret和configmap
kubectl edit cm test-dir-config -n namespace-0327
# 给configmap加上immutable: true
# 再次修改configmap的data，会报错 # * data: Forbidden: field is immutable when `immutable` is set
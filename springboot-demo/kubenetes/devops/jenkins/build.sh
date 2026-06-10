# 构建带 maven 环境的 jenkins 镜像
docker build -t 172.16.31.35:8858/wolfcode/jenkins-maven:v2 .
# 登录 harbor
docker login -uadmin 172.16.31.35:8858

# 推送镜像到 harbor
docker push 172.16.31.35:8858/wolfcode/jenkins-maven:v2
# 拉取镜像
docker pull 172.16.31.35:8858/wolfcode/jenkins-maven:v2

kubectl create secret docker-registry harbor-secret --docker-server=172.16.31.35:8858 --docker-username=admin --docker-password=wolfcode -n devops-test

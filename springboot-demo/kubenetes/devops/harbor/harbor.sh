# 下载 harbor 安装包
https://github.com/goharbor/harbor/releases/download/v2.14.1/harbor-offline-installer-v2.14.1.tgz
# 解压后执行 install.sh 就行
cp harbor.yml.tmpl harbor.yml
# 修改harbor.yml
# 1. 修改hostname为当前ip
# 2. 修改http.port 为可用端口
# 3. 注释掉https
# 4. 修改harbor_admin_password: wolfcode
./prepare
./install.sh
# 5. 登录harbor
http://172.16.31.35:8858/
admin wolfcode
# 再次运行harbor只需要 使用docker 启动
docker compose up -d

# 创建 harbor 访问账号密码（需要将下访问的配置信息改成你自己的）
kubectl create secret docker-registry harbor-secret --docker-server=172.16.31.35:8858 --docker-username=admin --docker-password=wolfcode -n kube-devops

# 添加harbor镜像仓库
vim /etc/docker/daemon.json
#{
#  "insecure-registries":["172.16.31.35:8858"]
#}
# 重启docker
systemctl restart docker
# 测试
docker login -uadmin 172.16.31.35:8858

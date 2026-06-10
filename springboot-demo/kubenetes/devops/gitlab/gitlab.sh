# 下载安装包
#wget https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el7/gitlab-ce-15.9.1-ce.0.el7.x86_64.rpm
curl https://packages.gitlab.com/gpg.key | sudo apt-key add -
sudo apt-add-repository "deb https://packages.gitlab.com/gitlab/gitlab-ce/ubuntu/ $(lsb_release -cs) main"

# 安装
#rpm -i gitlab-ce-15.9.1-ce.0.el7.x86_64.rpm
sudo apt-get update
sudo apt-get install gitlab-ce

# 编辑 /etc/gitlab/gitlab.rb 文件
# 修改 external_url 访问路径 http://<ip>:<port>
# 其他配置修改如下
gitlab_rails['time_zone'] = 'Asia/Shanghai'
puma['worker_processes'] = 2
sidekiq['concurrency'] = 8
postgresql['shared_buffers'] = "128MB"
postgresql['max_worker_processes'] = 4
prometheus_monitoring['enable'] = false

# 更新配置并重启
gitlab-ctl reconfigure
gitlab-ctl restart

# 查看默认密码
cat /etc/gitlab/initial_root_password
# 登录后修改默认密码 > 右上角头像 > Perferences > Password

# 修改系统配置：点击左上角三横 > Admin
# Settings > General > Account and limit > 取消 Gravatar enabled > Save changes

# 关闭用户注册功能
# Settings > General > Sign-up restrictions > 取消 Sign-up enabled > Save changes

# 开启 webhook 外部访问
# Settings > Network > Outbound requests > Allow requests to the local network from web hooks and services 勾选

# 设置语言为中文（全局）
# Settings > Preferences > Localization > Default language > 选择简体中文 > Save changes

# 设置当前用户语言为中文
# 右上角用户头像 > Preferences > Localization > Language > 选择简体中文 > Save changes

# 创建 gitlab 默认用户名密码 secret
echo root > ./username
echo wolfcode > password
kubectl create secret generic git-user-pass --from-file=./username --from-file=./password -n kube-devops


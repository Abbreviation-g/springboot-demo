# empty-dir的使用。实现容器间的数据共享
kubectl exec -it empty-dir-pd -c nginx-emptydir1 -n namespace-0327 -- sh
cd /cache
cat 1 > index.html
echo 4 >> index.html


kubectl exec -it empty-dir-pd -c nginx-emptydir2 -n namespace-0327 -- sh
cd /opt
ls
tail -f index.html

# 可以发现这两个容器实现了文件共享

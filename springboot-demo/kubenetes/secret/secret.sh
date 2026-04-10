#--------------------------------------------------------------------------------------------------------------------
kubectl create secret -h
kubectl create secret generic orig-secret --from-literal=username=admin --from-literal=password=ds-123 -n namespace-0327
kubectl describe secret orig-secret -n namespace-0327

kubectl create secret docker-registry -h
kubectl create secret docker-registry harbor-secret --docker-server=harbor.streamcomputing.com --docker-username=enjing.guo@streamcomputing.com --docker-password=Gej_25432 -n namespace-0327
# kubectl create secret docker-registry harbor-secret --docker-server=172.16.31.230 --docker-username=admin --docker-password=Harbor12345 -n namespace-0327
kubectl edit secret harbor-secret -n namespace-0327
echo 'eyJhdXRocyI6eyJodHRwczovL2hhcmJvci5zdHJlYW1jb21wdXRpbmcuY29tIjp7InVzZXJuYW1lIjoiZW5qaW5nLmd1b0BzdHJlYW1jb21wdXRpbmcuY29tIiwicGFzc3dvcmQiOiJHZWpfMjU0MzIiLCJhdXRoIjoiWlc1cWFXNW5MbWQxYjBCemRISmxZVzFqYjIxd2RYUnBibWN1WTI5dE9rZGxhbDh5TlRRek1nPT0ifX19' | base64 --decode
# {"auths":{"https://harbor.streamcomputing.com":{"username":"enjing.guo@streamcomputing.com","password":"Gej_25432","auth":"ZW5qaW5nLmd1b0BzdHJlYW1jb21wdXRpbmcuY29tOkdlal8yNTQzMg=="}}}

docker pull nginx:1.25
docker tag nginx:1.25 harbor.streamcomputing.com/opensource/nginx:1.25
#docker tag nginx:1.25 172.16.31.230/opensource/nginx:1.25
docker login -uenjing.guo@streamcomputing.com  harbor.streamcomputing.com
#docker login -uadmin  172.16.31.230
docker push harbor.streamcomputing.com/opensource/nginx:1.25
#docker push 172.16.31.230/opensource/nginx:1.25

#docker tag nginx:1.25 harbor.streamcomputing.com/apps/nginx:1.25
#docker push harbor.streamcomputing.com/apps/nginx:1.25
#echo 'acda@!123' | base64
#echo 'YWNkYUAhMTIzCg==' | base64 --decode


kubectl apply -f private-image-pull-pod.yaml

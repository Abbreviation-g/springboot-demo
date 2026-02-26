# build jar
# mvn clean install -Dmaven.test.skip=true

image_name="springboot-demo"
image_version="0.0.1"
jar_file="target/springboot-demo-0.0.1-SNAPSHOT.jar"
target_folder=docker

# move jar to docker/
echo "moving platform_image_name";
scp "${jar_file}" ${target_folder}

cd ${target_folder}
# build docker image
echo "docker build ${image_name}";
docker build -t "${image_name}:${image_version}" ./
# deploy project
echo "deploy ${image_name}";
docker compose stop
docker compose rm -f
docker compose up -d
#docker stop ${image_name}
#docker rm -f ${image_name}
#docker run -d -p 8763:8763 --restart=always --name ${image_name} ${image_name}:${image_version}
cd ../



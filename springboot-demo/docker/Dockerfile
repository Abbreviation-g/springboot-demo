FROM harbor.streamcomputing.com/devops/openjdk:21

ADD springboot-demo-0.0.1-SNAPSHOT.jar /springboot-demo-0.0.1-SNAPSHOT.jar

# 设置暴露的端口号
# EXPOSE 30003
# EXPOSE 9091

CMD ["java", "-jar", "-Duser.timezone=GMT+08", "-Dfile.encoding=UTF-8" , "/springboot-demo-0.0.1-SNAPSHOT.jar"]

**使用 Dubbo 开发 gRPC 服务**

参考dubbo JavaSDK手册 [基于 Spring Boot Starter 开发微服务应用](https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/quick-start/spring-boot/)，创建项目。

参考dubbo JavaSDK手册 [注册中心Nacos](https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/reference-manual/registry/nacos/)，将nacos配置为注册中心。

dubbo官方文档：[使用 Dubbo 开发 gRPC 服务](https://dubbo-next.staged.apache.org/zh-cn/overview/mannual/java-sdk/tasks/protocols/grpc/)

protobuf语法手册: [Language Guide (proto 3)](https://protobuf.dev/programming-guides/proto3/)

protobuf-java官方文档：[Java Generated Code Guide](https://protobuf.dev/reference/java/java-generated/#invocation)

完整代码参见 http://code.streamcomputing.com/testing/stc-ai-platform/-/tree/dubbo-nacos-demo

1. **新建项目**

父项目pom.xml

XML  
<br/>&lt;properties&gt;  
&lt;consumer.version&gt;1.1-SNAPSHOT&lt;/consumer.version&gt;  
&lt;provider.version&gt;1.1-SNAPSHOT&lt;/provider.version&gt;  
&lt;interface.version&gt;1.1-SNAPSHOT&lt;/interface.version&gt;  
&lt;project.build.sourceEncoding&gt;UTF-8&lt;/project.build.sourceEncoding&gt;  
<br/>&lt;grpc.consumer.version&gt;1.1-SNAPSHOT&lt;/grpc.consumer.version&gt;  
&lt;grpc.provider.version&gt;1.1-SNAPSHOT&lt;/grpc.provider.version&gt;  
&lt;grpc.interface.version&gt;1.1-SNAPSHOT&lt;/grpc.interface.version&gt;  
<br/>&lt;dubbo.version&gt;3.2.15&lt;/dubbo.version&gt;  
&lt;grpc.version&gt;1.58.0&lt;/grpc.version&gt;  
&lt;/properties&gt;  
<br/>&lt;dependencyManagement&gt;  
&lt;dependencies&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;  
&lt;artifactId&gt;spring-boot-dependencies&lt;/artifactId&gt;  
&lt;version&gt;${spring-boot.version}&lt;/version&gt;  
&lt;type&gt;pom&lt;/type&gt;  
&lt;scope&gt;import&lt;/scope&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;org.springframework.cloud&lt;/groupId&gt;  
&lt;artifactId&gt;spring-cloud-dependencies&lt;/artifactId&gt;  
&lt;version&gt;${spring-cloud.version}&lt;/version&gt;  
&lt;type&gt;pom&lt;/type&gt;  
&lt;scope&gt;import&lt;/scope&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;com.alibaba.cloud&lt;/groupId&gt;  
&lt;artifactId&gt;spring-cloud-alibaba-dependencies&lt;/artifactId&gt;  
&lt;version&gt;2023.0.0.0-RC1&lt;/version&gt;  
&lt;type&gt;pom&lt;/type&gt;  
&lt;scope&gt;import&lt;/scope&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;io.grpc&lt;/groupId&gt;  
&lt;artifactId&gt;grpc-bom&lt;/artifactId&gt;  
&lt;version&gt;${grpc.version}&lt;/version&gt;  
&lt;type&gt;pom&lt;/type&gt;  
&lt;scope&gt;import&lt;/scope&gt;  
&lt;/dependency&gt;  
&lt;/dependencies&gt;  
&lt;/dependencyManagement&gt;  
<br/>&lt;build&gt;  
&lt;pluginManagement&gt;  
&lt;plugins&gt;  
&lt;plugin&gt;  
&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;  
&lt;artifactId&gt;spring-boot-maven-plugin&lt;/artifactId&gt;  
&lt;version&gt;${spring-boot.version}&lt;/version&gt;  
&lt;/plugin&gt;  
&lt;/plugins&gt;  
&lt;/pluginManagement&gt;  
&lt;/build&gt;

2\. **新建模块**

2.1 **grpc-interface模块**

pom详解：

protobuf生成的代码引用了javax，java11之后的版本移除了javax，因此要导入javax.annotation。

os-maven-plugin插件用于读取操作系统信息

protobuf-maven-plugin插件用于根据proto文件生成java代码，生成的代码位于target/generated-sources/protobuf/java和target/generated-sources/protobuf/grpc-java

build-helper-maven-plugin插件执行add-source操作，将protobuf插件生成的代码添加为源代码

XML  
&lt;?xml version="1.0" encoding="UTF-8"?&gt;  
<project xmlns="http://maven.apache.org/POM/4.0.0"  
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
&lt;modelVersion&gt;4.0.0&lt;/modelVersion&gt;  
&lt;parent&gt;  
&lt;groupId&gt;com.streamcomputing.ai&lt;/groupId&gt;  
&lt;artifactId&gt;dubbo-nacos-demo&lt;/artifactId&gt;  
&lt;version&gt;1.0-SNAPSHOT&lt;/version&gt;  
&lt;/parent&gt;  
<br/>&lt;artifactId&gt;grpc-interface&lt;/artifactId&gt;  
<br/>&lt;properties&gt;  
&lt;source.level&gt;21&lt;/source.level&gt;  
&lt;target.level&gt;21&lt;/target.level&gt;  
&lt;project.build.sourceEncoding&gt;UTF-8&lt;/project.build.sourceEncoding&gt;  
&lt;javax.annotation.version&gt;1.3.2&lt;/javax.annotation.version&gt;  
<br/>&lt;maven-compiler-plugin.version&gt;3.7.0&lt;/maven-compiler-plugin.version&gt;  
&lt;os-maven-plugin.version&gt;1.6.1&lt;/os-maven-plugin.version&gt;  
&lt;protobuf-maven-plugin.version&gt;0.6.1&lt;/protobuf-maven-plugin.version&gt;  
&lt;protoc.version&gt;3.22.2&lt;/protoc.version&gt;  
&lt;build-helper-maven-plugin.version&gt;3.3.0&lt;/build-helper-maven-plugin.version&gt;  
&lt;/properties&gt;  
<br/>&lt;dependencies&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;org.apache.dubbo&lt;/groupId&gt;  
&lt;artifactId&gt;dubbo&lt;/artifactId&gt;  
&lt;version&gt;${dubbo.version}&lt;/version&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;io.grpc&lt;/groupId&gt;  
&lt;artifactId&gt;grpc-netty-shaded&lt;/artifactId&gt;  
&lt;version&gt;${grpc.version}&lt;/version&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;io.grpc&lt;/groupId&gt;  
&lt;artifactId&gt;grpc-all&lt;/artifactId&gt;  
&lt;version&gt;${grpc.version}&lt;/version&gt;  
&lt;exclusions&gt;  
&lt;exclusion&gt;  
&lt;artifactId&gt;grpc-netty&lt;/artifactId&gt;  
&lt;groupId&gt;io.grpc&lt;/groupId&gt;  
&lt;/exclusion&gt;  
&lt;/exclusions&gt;  
&lt;/dependency&gt;  
<br/>&lt;dependency&gt;  
&lt;groupId&gt;javax.annotation&lt;/groupId&gt;  
&lt;artifactId&gt;javax.annotation-api&lt;/artifactId&gt;  
&lt;version&gt;${javax.annotation.version}&lt;/version&gt;  
&lt;/dependency&gt;  
&lt;/dependencies&gt;  
<br/>&lt;build&gt;  
&lt;extensions&gt;  
&lt;extension&gt;  
&lt;groupId&gt;kr.motd.maven&lt;/groupId&gt;  
&lt;artifactId&gt;os-maven-plugin&lt;/artifactId&gt;  
&lt;version&gt;${os-maven-plugin.version}&lt;/version&gt;  
&lt;/extension&gt;  
&lt;/extensions&gt;  
&lt;plugins&gt;  
&lt;plugin&gt;  
&lt;groupId&gt;org.xolstice.maven.plugins&lt;/groupId&gt;  
&lt;artifactId&gt;protobuf-maven-plugin&lt;/artifactId&gt;  
&lt;version&gt;${protobuf-maven-plugin.version}&lt;/version&gt;  
&lt;configuration&gt;  
&lt;protocArtifact&gt;com.google.protobuf:protoc:${protoc.version}:exe:${os.detected.classifier}  
&lt;/protocArtifact&gt;  
&lt;pluginId&gt;grpc-java&lt;/pluginId&gt;  
&lt;pluginArtifact&gt;io.grpc:protoc-gen-grpc-java:${grpc.version}:exe:${os.detected.classifier}  
&lt;/pluginArtifact&gt;  
&lt;protocPlugins&gt;  
&lt;protocPlugin&gt;  
&lt;id&gt;dubbo&lt;/id&gt;  
&lt;groupId&gt;org.apache.dubbo&lt;/groupId&gt;  
&lt;artifactId&gt;dubbo-compiler&lt;/artifactId&gt;  
&lt;version&gt;${dubbo.version}&lt;/version&gt;  
&lt;mainClass&gt;org.apache.dubbo.gen.tri.Dubbo3TripleGenerator&lt;/mainClass&gt;  
&lt;/protocPlugin&gt;  
&lt;/protocPlugins&gt;  
&lt;/configuration&gt;  
&lt;executions&gt;  
&lt;execution&gt;  
&lt;goals&gt;  
&lt;goal&gt;compile&lt;/goal&gt;  
&lt;goal&gt;compile-custom&lt;/goal&gt;  
&lt;/goals&gt;  
&lt;/execution&gt;  
&lt;/executions&gt;  
&lt;/plugin&gt;  
&lt;plugin&gt;  
&lt;groupId&gt;org.codehaus.mojo&lt;/groupId&gt;  
&lt;artifactId&gt;build-helper-maven-plugin&lt;/artifactId&gt;  
&lt;version&gt;${build-helper-maven-plugin.version}&lt;/version&gt;  
&lt;executions&gt;  
&lt;execution&gt;  
&lt;phase&gt;generate-sources&lt;/phase&gt;  
&lt;goals&gt;  
&lt;goal&gt;add-source&lt;/goal&gt;  
&lt;/goals&gt;  
&lt;configuration&gt;  
&lt;sources&gt;  
&lt;source&gt;target/generated-sources/protobuf/java&lt;/source&gt;  
&lt;source&gt;target/generated-sources/protobuf/grpc-java&lt;/source&gt;  
&lt;/sources&gt;  
&lt;/configuration&gt;  
&lt;/execution&gt;  
&lt;/executions&gt;  
&lt;/plugin&gt;  
&lt;/plugins&gt;  
&lt;/build&gt;  
&lt;/project&gt;

在src/main目录下创建proto文件夹，在proto文件夹下创建greeter.proto文件。

ProtoBuf  
syntax = "proto3";  
<br/>option java_multiple_files = true;  
<br/>package com.streamcomputing.ai;  
<br/>message GreeterRequest {  
string name = 1;  
}  
<br/>message GreeterReply {  
string message = 1;  
}  
<br/>service Greeter{  
rpc greet(GreeterRequest) returns (GreeterReply);  
}

构建grpc-interface

执行mvn clean compile，即可生成proto对应的java代码

2.2 **grpc-consumer模块**

导入dubbo和spring相关依赖。

pom.xml

XML  
&lt;?xml version="1.0" encoding="UTF-8"?&gt;  
<project xmlns="http://maven.apache.org/POM/4.0.0"  
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
&lt;modelVersion&gt;4.0.0&lt;/modelVersion&gt;  
&lt;parent&gt;  
&lt;groupId&gt;com.streamcomputing.ai&lt;/groupId&gt;  
&lt;artifactId&gt;dubbo-nacos-demo&lt;/artifactId&gt;  
&lt;version&gt;1.0-SNAPSHOT&lt;/version&gt;  
&lt;/parent&gt;  
<br/>&lt;artifactId&gt;grpc-consumer&lt;/artifactId&gt;  
<br/>&lt;properties&gt;  
&lt;maven.compiler.source&gt;21&lt;/maven.compiler.source&gt;  
&lt;maven.compiler.target&gt;21&lt;/maven.compiler.target&gt;  
&lt;project.build.sourceEncoding&gt;UTF-8&lt;/project.build.sourceEncoding&gt;  
&lt;/properties&gt;  
<br/>&lt;dependencies&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;com.streamcomputing.ai&lt;/groupId&gt;  
&lt;artifactId&gt;grpc-interface&lt;/artifactId&gt;  
&lt;version&gt;1.0-SNAPSHOT&lt;/version&gt;  
&lt;/dependency&gt;  
<br/>&lt;dependency&gt;  
&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;  
&lt;artifactId&gt;spring-boot-starter&lt;/artifactId&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;org.springframework.cloud&lt;/groupId&gt;  
&lt;artifactId&gt;spring-cloud-starter&lt;/artifactId&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;com.alibaba.cloud&lt;/groupId&gt;  
&lt;artifactId&gt;spring-cloud-starter-alibaba-nacos-discovery&lt;/artifactId&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;org.apache.dubbo&lt;/groupId&gt;  
&lt;artifactId&gt;dubbo&lt;/artifactId&gt;  
&lt;version&gt;${dubbo.version}&lt;/version&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;org.apache.dubbo&lt;/groupId&gt;  
&lt;artifactId&gt;dubbo-spring-boot-autoconfigure&lt;/artifactId&gt;  
&lt;version&gt;${dubbo.version}&lt;/version&gt;  
&lt;/dependency&gt;  
&lt;dependency&gt;  
&lt;groupId&gt;org.projectlombok&lt;/groupId&gt;  
&lt;artifactId&gt;lombok&lt;/artifactId&gt;  
&lt;scope&gt;provided&lt;/scope&gt;  
&lt;/dependency&gt;  
&lt;/dependencies&gt;  
&lt;/project&gt;

application.yml

根据dubbo官网，

就像在 [Triple协议规范](https://dubbo.apache.org/zh-cn/overview/reference/protocols/triple/) 中所描述的，Triple 协议与 gRPC 协议保持 100% 兼容，同时在易用性方面有了非常大的提升（比如支持 cURL、浏览器直接访问等），可以说 Triple 是一个更好用的 gRPC 设计与实现。

因此此处的protocol配置为tri。

YAML  
spring:  
application:  
name: dubbo-nacos-demo-grpc-consumer  
<br/>dubbo:  
application:  
qos-enable: false  
enable-file-cache: false  
protocol:  
name: tri  
port: -1  
id: tri  
registry:  
address: nacos://172.16.31.80:8848?namespace=demo  
username: nacos  
password: nacos  
parameters.register-consumer-url: true

Task.java

Java  
@Slf4j  
@Component  
public class Task implements CommandLineRunner {  
@DubboReference  
private Greeter greeter;  
<br/>@Override  
public void run(String... args) throws Exception {  
Timer timer = new Timer();  
TimerTask task = new TimerTask() {  
@Override  
public void run() {  
GreeterRequest request = GreeterRequest._newBuilder_().setName("\[grpc client\]")  
.build();  
GreeterReply reply = greeter.greet(request);  
_log_.info("GrpcClient.unary");  
_log_.info("Grpc client received reply <- \\"{}\\"", reply.getMessage());  
_log_.info("\\n\\n");  
}  
};  
timer.schedule(task, 5\*1000, 10\*1000);  
}  
}

GrpcConsumerApplication.java

Java  
@SpringBootApplication  
@EnableDubbo  
public class GrpcConsumerApplication {  
<br/>public static void main(String\[\] args) {  
SpringApplication._run_(GrpcConsumerApplication.class, args);  
}  
}

2.3 **grpc-provider模块**

pom.xml 与 grpc-consumer模块一致

application.yml

YAML  
spring:  
application:  
name: dubbo-nacos-demo-grpc-provider  
<br/>dubbo:  
application:  
qos-enable: false  
enable-file-cache: false  
protocol:  
name: tri  
port: -1  
id: tri  
registry:  
address: nacos://172.16.31.80:8848?namespace=demo  
username: nacos  
password: nacos  
provider:  
loadbalance: roundrobin

GrpcProviderApplication.java

Java  
@SpringBootApplication  
@EnableDubbo  
public class GrpcProviderApplication {  
public static void main(String\[\] args) {  
SpringApplication._run_(GrpcProviderApplication.class, args);  
}  
}

TriGreeterImpl.java

Java  
@Slf4j  
@DubboService  
public class TriGreeterImpl extends DubboGreeterTriple.GreeterImplBase {  
public TriGreeterImpl() {  
}  
<br/>@Override  
public GreeterReply greet(GreeterRequest request) {  
_log_.info("Server received greet request {}", request);  
return GreeterReply._newBuilder_()  
.setMessage("hello," + request.getName())  
.build();  
}  
}

3\. **构建和运行**

构建好三个模块后，先执行grpc-interface模块的clean compile，待grpc-interface生成代码之后，另外两个模块就不再报错了。

先运行grpc-provider的GrpcProviderApplication

再运行grpc-consumer的GrpcConsumerApplication

打开nacos，可以看到服务列表中有了三个服务

观察控制台，可以看到provider和consumer调通

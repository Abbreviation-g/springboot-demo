FROM jenkins/jenkins:2.567-jdk21
ADD ./apache-maven-3.9.0-bin.tar.gz /usr/local/
ADD ./sonar-scanner-4.8.0.2856-linux/ /usr/local/sonar-scanner

ENV MAVEN_HOME=/usr/local/apache-maven-3.9.0
ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

USER root
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
USER jenkins


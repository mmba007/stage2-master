FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8761
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
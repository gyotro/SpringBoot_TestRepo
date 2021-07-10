FROM adoptopenjdk:8-jdk
EXPOSE 8080
MAINTAINER giovanni <giovanni.dintrono>
ADD target/demoSpring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
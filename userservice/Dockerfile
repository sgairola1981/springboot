FROM openjdk:17-jdk-slim-buster
LABEL mentainer="sgairola@gmail.com"
WORKDIR /app
COPY target/user-service.jar /app/img-user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]
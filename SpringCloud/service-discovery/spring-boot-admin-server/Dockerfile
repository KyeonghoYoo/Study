FROM openjdk:11-jdk-slim

VOLUME /app

COPY build/libs/spring-boot-admin-server-1.0.0.jar spring-boot-admin-server.jar

ENTRYPOINT ["java", "-jar", "spring-boot-admin-server.jar"]
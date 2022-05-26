FROM openjdk:11-jdk-slim

VOLUME /app

COPY /apiEncryptionKey.jks apiEncryptionKey.jks

COPY /build/libs/config-service-1.0.0.jar config-service-1.0.0.jar

ENTRYPOINT ["java", "-jar", "config-service-1.0.0.jar"]
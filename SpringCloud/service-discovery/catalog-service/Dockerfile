FROM openjdk:11-jdk-slim

VOLUME /app

COPY build/libs/catalog-service-1.0.0.jar catalog-service.jar

ENTRYPOINT ["java", "-jar", "catalog-service.jar"]
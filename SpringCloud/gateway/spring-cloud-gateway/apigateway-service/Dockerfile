FROM openjdk:11-jdk-slim

VOLUME /app

COPY target/apigateway-service-1.0.0.jar apigateway-service.jar

ENTRYPOINT ["java", "-jar", "apigateway-service.jar"]
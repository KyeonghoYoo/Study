FROM openjdk:11-jdk-slim

VOLUME /app

COPY build/libs/order-service-1.0.0.jar order-service.jar

ENTRYPOINT ["java", "-jar", "order-service.jar"]
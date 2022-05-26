FROM openjdk:11-jdk-slim

VOLUME /app

COPY target/kyeongho-discovery-service-1.0.0.jar kyeongho-discovery-service.jar

ENTRYPOINT ["java", "-jar", "kyeongho-discovery-service.jar"]
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Eureka-server-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["/wait-for-it.sh", "--", "java", "-jar", "app.jar"]

EXPOSE 8761

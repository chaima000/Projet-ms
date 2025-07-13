FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Bloc-ms-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["/wait-for-it.sh", "mongodb:27017", "--", "java", "-jar", "app.jar"]

EXPOSE 8200

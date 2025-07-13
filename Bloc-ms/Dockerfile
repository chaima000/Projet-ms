FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/bloc-ms.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["/wait-for-it.sh", "mongodb:27017", "--", "java", "-jar", "app.jar"]

EXPOSE 8200

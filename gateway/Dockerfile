FROM openjdk:17-jdk-slim
WORKDIR /app

# Copier le fichier JAR généré
COPY target/gateway-0.0.1-SNAPSHOT.jar app.jar

# Commande pour démarrer le microservice
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8300
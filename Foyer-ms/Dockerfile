FROM openjdk:17-jdk-slim

WORKDIR /app

# Copier le jar compilé (assure-toi que le nom correspond à ton build)

COPY target/Foyer-ms-0.0.1-SNAPSHOT.jar app.jar


# Attendre que le service mysql soit prêt avant de lancer l'app
ENTRYPOINT ["java", "-jar", "app.jar"]

# Exposer le port configuré dans ton app (8100)
EXPOSE 8100

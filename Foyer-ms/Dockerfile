FROM openjdk:17-jdk-slim

WORKDIR /app

# Copier le jar compilé (assure-toi que le nom correspond à ton build)

COPY target/Foyer-ms-0.0.1-SNAPSHOT.jar app.jar

# Copier le script wait-for-it.sh dans le conteneur
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

# Attendre que le service mysql soit prêt avant de lancer l'app
ENTRYPOINT ["/wait-for-it.sh", "mysqldb:3306", "--", "java", "-jar", "app.jar"]

# Exposer le port configuré dans ton app (8100)
EXPOSE 8100

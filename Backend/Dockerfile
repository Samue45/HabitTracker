# Usa una imagen base con el JRE de Java
FROM openjdk:11-jre-slim

# Copia el archivo JAR de la aplicación al contenedor
COPY target/HabitTracker-0.0.1-SNAPSHOT.jar /app/HabitTracker-0.0.1-SNAPSHOT.jar

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/HabitTracker-0.0.1-SNAPSHOT.jar"]

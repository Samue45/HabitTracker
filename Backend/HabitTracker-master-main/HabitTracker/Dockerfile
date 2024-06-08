FROM openjdk:21-jdk-slim

WORKDIR /app

# Copiar el archivo JAR generado al directorio de trabajo
COPY HabitTracker-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]

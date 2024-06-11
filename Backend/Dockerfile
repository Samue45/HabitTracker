FROM openjdk:21-jdk-slim

WORKDIR /main

# Copiar el archivo JAR generado al directorio de trabajo
COPY target/HabitTracker-0.0.1-SNAPSHOT.jar /app

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "HabitTracker-0.0.1-SNAPSHOT.jar"]

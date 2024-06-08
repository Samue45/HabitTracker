# Usar una imagen base de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al directorio de trabajo
COPY target/habittracker.jar app.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]

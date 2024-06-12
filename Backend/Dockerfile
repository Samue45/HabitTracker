# Usa una imagen base de Ubuntu para instalar OpenJDK 21 y Maven
FROM ubuntu:20.04

# Configurar la zona horaria para evitar interacciones durante la instalación
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get install -y tzdata && \
    ln -fs /usr/share/zoneinfo/Etc/UTC /etc/localtime && \
    dpkg-reconfigure --frontend noninteractive tzdata

# Instala OpenJDK 21
RUN apt-get update && apt-get install -y wget gnupg software-properties-common && \
    wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add - && \
    add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/ && \
    apt-get update && apt-get install -y adoptopenjdk-21-hotspot

# Instala Maven
RUN apt-get install -y maven

# Crea y define el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración Maven y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto del código fuente y construye la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Usa una imagen de OpenJDK 21 para ejecutar la aplicación
FROM adoptopenjdk:21-jre-hotspot

# Crea y define el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR construido desde la imagen Maven
COPY --from=0 /app/target/HabitTracker-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]



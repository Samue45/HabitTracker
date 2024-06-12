# Usa una imagen base de OpenJDK 21 para construir la aplicación
FROM eclipse-temurin:21-jdk AS build

# Instala Maven
RUN apt-get update && \
    apt-get install -y maven

# Crea y define el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración Maven y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto del código fuente y construye la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Usa una imagen de OpenJDK 21 para ejecutar la aplicación
FROM eclipse-temurin:21-jre

# Crea y define el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR construido desde la imagen Maven
COPY --from=build /app/target/HabitTracker-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]


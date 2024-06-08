# Habit Tracker

¡Bienvenido a Habit Tracker!

Esta es una aplicación desarrollada con Java, Spring, PostgreSQL (utilizando Supabase como servicio de base de datos), Node.js, HTML, CSS, TypeScript y Angular CLI.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados lo siguiente en tu sistema:

- **Java Development Kit (JDK)**: Necesario para ejecutar el backend Java. Puedes descargarlo desde [aquí](https://www.oracle.com/es/java/technologies/downloads/#java21).
- **Node.js**: Necesario para ejecutar el frontend Angular. Puedes descargarlo desde [aquí](https://nodejs.org/).
- **Angular CLI**: Herramienta de línea de comandos para Angular. Puedes instalarla globalmente usando el siguiente comando: `npm install -g @angular/cli`.
- **Editor de Texto o IDE**: Se recomienda tener un editor de texto o un IDE de tu elección para editar y revisar el código.

## Configuración del Backend (Java, Spring)

1. **Clonar el Repositorio**:
   Clona este repositorio en tu máquina local usando Git.

2. **Configurar la Conexión con Supabase**:
   - Regístrate en [Supabase](https://supabase.io/) y crea un proyecto.
   - Obtén la URL de la API y la clave de la API de tu proyecto de Supabase.
   - Actualiza las credenciales de la base de datos en el archivo `application.properties` en la carpeta `src/main/resources`.

3. **Construir y Ejecutar el Backend**:
   - Abre una terminal en la carpeta del proyecto backend.
   - Ejecuta el siguiente comando:
     ```
     ./mvnw spring-boot:run
     ```
   - El backend se ejecutará en `http://localhost:8080`.

## Configuración del Frontend (Node.js, Angular)

1. **Instalar las Dependencias**:
   - Abre una terminal en la carpeta del proyecto frontend.
   - Ejecuta el siguiente comando:
     ```
     npm install
     ```

2. **Configurar la Conexión con el Backend**:
   - Actualiza la URL de la API en el archivo de configuración de Angular (`environment.ts`) para apuntar a tu backend local.

3. **Ejecutar el Frontend**:
   - Ejecuta el siguiente comando:
     ```
     ng serve
     ```
   - El frontend estará disponible en `http://localhost:4200`.

## ¡Listo!

¡Ahora deberías tener la aplicación en funcionamiento! Abre tu navegador y visita `http://localhost:4200` para ver la aplicación en acción. Si encuentras algún problema durante el proceso de configuración, no dudes en contactar al equipo de desarrollo.

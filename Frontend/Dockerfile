# Usar una imagen base de Node.js (recomendamos una versión LTS reciente)
FROM node:20-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY . .

# Instalar las dependencias
RUN npm install

# Construir la aplicación Angular
RUN npm run build

# Instalar un servidor HTTP para servir la aplicación
RUN npm install -g http-server

# Exponer el puerto en el que el servidor HTTP se ejecutará
EXPOSE 8080

# Comando para ejecutar el servidor HTTP y servir la aplicación Angular
CMD ["http-server", "dist/tu-app-angular"]

FROM node:18-alpine AS build

WORKDIR /app

# Copia los archivos de package.json y package-lock.json
COPY package*.json ./

# Instala las dependencias
RUN npm install

# Copia el resto del código fuente
COPY . .

# Construye la aplicación
RUN npm run build

# Usa Nginx para servir la aplicación construida
FROM nginx:alpine

# Copia los archivos construidos al contenedor
COPY --from=build /app/build /usr/share/nginx/html

# Expone el puerto que Nginx utilizará
EXPOSE 80

# Comando para ejecutar Nginx
CMD ["nginx", "-g", "daemon off;"]

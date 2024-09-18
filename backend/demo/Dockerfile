# Usa una imagen base con JDK
FROM openjdk:17-jdk-slim

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que la aplicación utilizará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

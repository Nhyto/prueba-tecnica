# Fase 1: Compilar la aplicación usando Maven
FROM maven:3.8.4-openjdk-11-slim AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y resolver las dependencias de Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente de la aplicación y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Ejecutar la aplicación usando una imagen ligera de Java
FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado en la fase de compilación
COPY --from=build /app/target/prueba-tecnica-0.0.1-SNAPSHOT.jar /app/prueba-tecnica.jar

# Exponer el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "prueba-tecnica.jar"]
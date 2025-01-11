# Imagen modelo
FROM eclipse-temurin:21.0.5_11-jdk-ubi9-minimal

# Puerto de la aplicacion
EXPOSE 8080

# Directorio de trabajo
WORKDIR /app

# Copiar pom.xml & maven wrapper (archivo - ubicacion)
COPY ./pom.xml /app
COPY ./.mvn /app/.mvn
COPY ./mvnw /app

# Descargar dependencias (descargar y no ejecutar proyecto)
RUN ./mvnw dependency:go-offline

# Copiar proyecto
COPY ./src /app/src

# Construir proyecto (saltar tests)
RUN ./mvnw clean install -DskipTests

# Ejecutar proyecto cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "/app/target/wallet-backend-0.0.1-SNAPSHOT.jar"]

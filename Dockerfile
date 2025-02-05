# Imagen modelo
FROM eclipse-temurin:21.0.6_7-jdk

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

RUN apt-get update && apt-get install -y netcat-openbsd

# Ejecutar proyecto cuando se inicie el contenedor
ENTRYPOINT ["sh", "-c", "while ! nc -z reto-db 3306; do sleep 1; done; java -jar /app/target/reto-0.0.1-SNAPSHOT.jar"]
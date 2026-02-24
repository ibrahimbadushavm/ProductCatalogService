# Build stage: compile and package with Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

LABEL authors="ibrahimbadushavm"

# Copy pom and download dependencies to leverage layer caching
COPY pom.xml .
RUN mvn -B -Dmaven.test.skip=true dependency:go-offline

# Copy source and build the application
COPY src ./src
RUN mvn -B -Dmaven.test.skip=true package

# Runtime stage: lightweight JRE
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY --from=build /workspace/${JAR_FILE} app.jar

ENV ELASTICSEARCH_URIS=${ELASTICSEARCH_URIS} \
    EUREKA_SERVER_HOST=${EUREKA_SERVER_HOST} \
    EUREKA_SERVER_PORT=${EUREKA_SERVER_PORT} \
    OAUTH2_ISSUER_URI=${OAUTH2_ISSUER_URI} \
    PRODUCT_SERVICE_DB_PASSWORD=${PRODUCT_SERVICE_DB_PASSWORD} \
    PRODUCT_SERVICE_DB_URL=${PRODUCT_SERVICE_DB_URL} \
    PRODUCT_SERVICE_DB_USERNAME=${PRODUCT_SERVICE_DB_USERNAME} \
    PRODUCT_SERVICE_PORT=${PRODUCT_SERVICE_PORT} \
    REDIS_HOST=${REDIS_HOST} \
    REDIS_PORT=${REDIS_PORT} \
    JAVA_OPTS=""


ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

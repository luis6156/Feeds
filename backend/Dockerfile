FROM maven:3.9.4-eclipse-temurin-17 AS build-env

WORKDIR /app
COPY . .
RUN mvn package spring-boot:repackage -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build-env /app/target .

ENTRYPOINT ["java", "-jar", "backend-1.0.0.jar"]
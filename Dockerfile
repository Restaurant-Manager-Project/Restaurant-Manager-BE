FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /usr/src/app
COPY . .
RUN mvn install

FROM openjdk:17-alpine
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/Restaurant-Manager-BE-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]

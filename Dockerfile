FROM maven:3.9.9-eclipse-temurin-21-alpine as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM alpine/java:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
EXPOSE 9095

CMD ["java", "-jar", "app.jar"]
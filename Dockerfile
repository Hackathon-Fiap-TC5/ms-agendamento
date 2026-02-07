FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# JVM flags optimized for Cloud Run (512MB container)
CMD ["java", \
     "-XX:+UseG1GC", \
     "-XX:MaxRAMPercentage=75.0", \
     "-XX:+TieredCompilation", \
     "-XX:TieredStopAtLevel=1", \
     "-Djava.security.egd=file:/dev/./urandom", \
     "-jar", "app.jar"]
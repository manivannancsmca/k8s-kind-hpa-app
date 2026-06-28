FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
# Using alpine means we use sh instead of bash, which mvnw handles perfectly
RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw clean package -DskipTests

# --- Runtime Stage ---
# Switching to the Alpine version of the JRE drops the base size drastically
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Optional: Add a non-root user for better security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
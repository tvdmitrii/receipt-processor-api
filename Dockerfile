# Maven Build Stage
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /build
# Copy source files required for the build
COPY ./src ./src
COPY ./pom.xml ./pom.xml
RUN mvn clean package

# Run project
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /build/target/receipt-processor-api*.jar api.jar

# Expose port 8080
EXPOSE 8080

# Default executable
ENTRYPOINT ["java", "-jar", "api.jar"]
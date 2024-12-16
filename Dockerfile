# Get source code from GitHub repository
FROM alpine/git:latest AS source
WORKDIR /source
RUN git clone https://github.com/tvdmitrii/receipt-processor-api.git

# Use Maven to build the code
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /build
COPY --from=source /source/receipt-processor-api/ ./
RUN mvn clean package

# Run project
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /build/target/receipt-processor-api*.jar api.jar

# Expose port 8080
EXPOSE 8080

# Default executable
ENTRYPOINT ["java", "-jar", "api.jar"]
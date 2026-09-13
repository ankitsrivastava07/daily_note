# ==============================================================================
# Stage 1: Build the Application
# ==============================================================================
FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copy pom.xml and download dependencies to optimize layer caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and package the JAR file
COPY src ./src
RUN mvn clean package -DskipTests

# ==============================================================================
# Stage 2: Runtime Image with AWS Lambda Web Adapter (LWA)
# ==============================================================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy AWS Lambda Web Adapter extension
COPY --from=public.ecr.aws/awsguru/aws-lambda-adapter:0.8.4 \
     /lambda-adapter /opt/extensions/lambda-adapter

# Copy the generated application JAR from the builder stage
COPY --from=builder /build/target/*.jar app.jar

# Configure AWS Lambda Web Adapter environment variable
ENV PORT=9091
ENV AWS_LWA_PORT=9091

EXPOSE 9091

# Startup command optimized for fast Lambda execution
ENTRYPOINT ["java", "-XX:+TieredCompilation", "-XX:TieredStopAtLevel=1", "-jar", "app.jar"]
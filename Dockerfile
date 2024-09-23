#checkov:skip=CKV2_GHA_1

# Final Stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Install curl
RUN apk --no-cache add curl=8.9.1-r2

# Copy the backend JAR file
COPY target/*.jar /app/backend.jar

# Expose ports
EXPOSE 80

# Healthcheck
HEALTHCHECK --interval=5m --timeout=3s \
    CMD ["curl", "-f", "http://localhost:80/q/health/live"]

# Add a user and switch to it
RUN adduser -D srv_user
USER srv_user

# Start application
CMD ["java", "-jar", "/app/backend.jar"]
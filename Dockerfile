# Final Stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Install nginx
RUN apk add --no-cache nginx

# Copy the backend JAR file
COPY target/*.jar /app/backend.jar

# Expose ports
EXPOSE 80

# Start application
CMD ["java -jar /app/backend.jar"]
# Official openjdk runtime as parent image
FROM openjdk:11-jre-slim

# Set working directory to /app
WORKDIR /app

# Copy current directory contents to /app container
COPY . /app/

# Run Maven to build application
RUN ./mvnw package

# Set startup command to run application
CMD ["java", "-jar", "target/cake-shop.jar"]


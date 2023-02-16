# Official openjdk runtime as parent image
FROM debian:latest

RUN apt-get update && \
    apt-get install -y openjdk-11-jdk
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64

# Set working directory to /app
WORKDIR /app

# Copy current directory contents to /app container
COPY . /app/

# Run Maven to build application
RUN ./mvnw package

# Set startup command to run application
CMD ["java", "-jar", "target/cake-shop.jar"]


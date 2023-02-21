# Use an official Maven runtime as a parent image
FROM maven:3.5.2-jdk-8

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file into the container
COPY pom.xml /tmp/pom.xml
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

# Copy the rest of the application code into the container
COPY .mvn/wrapper/maven-wrapper.jar /app/

# Expose the port that the application will run on
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "target/mycake-shop.jar"]

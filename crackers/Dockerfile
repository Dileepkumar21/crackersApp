# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file to the container
COPY crackers-0.0.1-SNAPSHOT.jar /app/crackers-0.0.1-SNAPSHOT.jar

# Define the command to run the jar file
ENTRYPOINT ["java", "-jar", "/app/crackers-app.jar"]

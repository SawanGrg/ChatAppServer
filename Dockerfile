# Use the official OpenJDK 11 image from Docker Hub
FROM adoptopenjdk/openjdk11:alpine-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container at the specified path
COPY target/ChatAppServer.jar /app

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "ChatAppServer.jar"]

# Use a lightweight Alpine OpenJDK image
FROM adoptopenjdk/openjdk11:alpine-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at the specified path
COPY target/ChatAppServer.jar /app/ChatAppServer.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "ChatAppServer.jar"]

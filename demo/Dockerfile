# Use the official Maven image as a parent image
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project's pom.xml file to the container
COPY pom.xml .

# Copy the entire project to the container
COPY src ./src

# Build the application using Maven
RUN mvn clean package

# Use the official OpenJDK 17 runtime image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file generated during the build phase to the container
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "app.jar"]





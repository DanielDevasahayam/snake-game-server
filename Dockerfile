# Use an official Maven image to build the application
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run the application
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar snake-game-1.0-SNAPSHOT.jar

# Expose the port (if your application uses a specific port)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "snake-game-1.0-SNAPSHOT.jar"]
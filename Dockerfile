# Create a new image for running the application
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the Maven project
RUN mvn clean package -DskipTests

# Use a lightweight Java base image - adjust the version as needed
FROM openjdk:24-jdk-slim

# Create a working directory for your app
WORKDIR /app

# Copy the JAR file from the 'artifacts' directory (created by your build step)
COPY --from=build /app/target/*.jar app.jar

RUN apt update && apt install curl -y

# Define the command to execute when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]

#use maven to complie the code

#FROM maven:3.8.3-openjdk-17-slim AS build
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean package

#run with local.properties env
#docker run -e "SPRING_PROFILES_ACTIVE=local" -p 8080:8080 -t springio/gs-spring-boot-docker

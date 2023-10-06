FROM amazoncorretto:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY build/libs/chrispybaconend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Specify the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]

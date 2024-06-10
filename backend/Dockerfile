# Use official OpenJDK image from the Docker Hub
FROM eclipse-temurin:17-jdk-jammy as base

# Set the working directory inside the container
WORKDIR /app

# Copy the mvnw wrapper with executable permissions and the pom.xml file
COPY --chmod=0755 mvnw mvnw
COPY .mvn .mvn/
COPY pom.xml .

# Download dependencies as a separate step to take advantage of Docker's caching
RUN ./mvnw dependency:go-offline -B

# Copy the source code into the container
COPY src ./src

# Command will be defined in docker-compose.yml for hot-reloading

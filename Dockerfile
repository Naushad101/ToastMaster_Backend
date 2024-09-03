# Stage 1: Build and generate .jsa and .lst files
FROM openjdk:21-jdk AS builder

# Set the working directory
WORKDIR /app

# Copy the Spring Boot application JAR to the container
COPY build/libs/ToastMasterApp-0.0.1-SNAPSHOT.jar /app/ToastMasterApp.jar




# Stage 2: Final image to run the application
FROM openjdk:21-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR and the .jsa file from the builder stage
COPY --from=builder /app/ToastMasterApp.jar /app/ToastMasterApp.jar
# COPY --from=builder /app/classes.jsa /app/classes.jsa
#  COPY --from=builder /app/classes.lst /app/classes.lst
# COPY --from=builder /app/app-cds.jsa /app/app-cds.jsa


# Expose the port that the application will run on
EXPOSE 8081

# Run the Spring Boot application with the .jsa file
ENTRYPOINT ["java", "-XX:ArchiveClassesAtExit=cds-demo.jsa", "-Xlog:class+load:file=cds.log", "-jar", "-jar", "/app/ToastMasterApp.jar"]

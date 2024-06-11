FROM openjdk:17-jdk-alpine
EXPOSE 9001
ARG JAR_FILE=target/*.jar
COPY ./target/users-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:17-jdk-alpine
EXPOSE 9003
ARG JAR_FILE=target/*.jar
COPY ./target/users-healths-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
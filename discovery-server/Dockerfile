FROM openjdk:17-jdk-alpine
EXPOSE 8761
ARG JAR_FILE=target/*.jar
COPY ./target/discovery-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
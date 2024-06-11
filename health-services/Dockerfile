FROM openjdk:17-jdk-alpine
EXPOSE 9002
ARG JAR_FILE=target/*.jar
COPY ./target/health-services-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
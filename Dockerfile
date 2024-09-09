FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/tickets-0.0.1-SNAPSHOT.jar /app/tickets-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java","-jar","tickets-0.0.1-SNAPSHOT.jar"]
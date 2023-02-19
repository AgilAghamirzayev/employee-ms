FROM openjdk:17-alpine as build
MAINTAINER agil.aghamirzayev
COPY target/employee-ms-0.0.1-SNAPSHOT.jar employee-ms-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/employee-ms-0.0.1-SNAPSHOT.jar"]
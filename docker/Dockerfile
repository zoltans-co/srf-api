FROM openjdk:17-jdk
VOLUME /tmp
ARG JAR_FILE=/srf-api-2023.1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
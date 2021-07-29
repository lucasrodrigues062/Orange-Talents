FROM openjdk:11
ARG JAR_FILE=build/libs/keymanager-rest-0.1-all.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
FROM openjdk:24-slim
LABEL authors="Raviraj Shinde"

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} quiz-app.jar

ENTRYPOINT ["java", "-jar", "/app/quiz-app.jar"]
EXPOSE 8080
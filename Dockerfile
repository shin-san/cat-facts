ARG MYAPP_IMAGE=openjdk:15-jdk-alpine

FROM $MYAPP_IMAGE
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
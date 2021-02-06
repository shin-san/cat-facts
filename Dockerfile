ARG MYAPP_IMAGE
ARG MAVEN_BUILD

# Build stage
#
FROM $MAVEN_BUILD AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM $MYAPP_IMAGE
ARG JAR_FILE=/home/app/target/*.jar
COPY --from=build ${JAR_FILE} /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
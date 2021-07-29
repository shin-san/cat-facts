ARG MYAPP_IMAGE

FROM $MYAPP_IMAGE
ARG JAR_FILE=/home/app/target/*.jar
COPY mvnw /home/mvnw
COPY .mvn /home/.mvn
COPY src /home/src
COPY pom.xml /home/
WORKDIR /home
RUN ./mvnw clean package
COPY --from=build ${JAR_FILE} /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

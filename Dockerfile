FROM openjdk:8-jdk-alpine
RUN addgroup -S urlapp && adduser -S urlapp -G urlapp
USER urlapp:urlapp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} url-services.jar
ENTRYPOINT ["java","-jar","/url-services.jar"]
# URL App - Technical test for Neueda Technologies

## Description
This service has APIs to create short URLs and fetch and redirect to actual url based on the short URL

### url-services

## Dependencies

The list of software on which this micros service depends on is

- JDK 8 and above
- Gradle 6.8 and above

## Checklist

The following is a check list required for the Micro Service:
- As part of this implementation H2 in memory database  has been considered. This can be replaced with any persistent database
- Application can be build using simple gradle commands
- Service has been dockerized 

## APIs
API details can be viewed by running the application locally and accessing the same via http://localhost:8080/swagger-ui.html 

## Docker Build
- docker build --build-arg JAR_FILE=build/libs/*.jar -t urlapp/url-services .
- docker run -p 8080:8080 urlapp/url-services

## Gradle commands
### gradle clean 
This command can be used to clean the application

### gradle build
This command can be used to build the application and generate the artifacts

### gradle bootRun
This command can be used to start the application and test the APIs
# CRUD JWT Test project

This is a practice project based on **Spring Platform Brussels-SR4**. Purpose of this project is to sharpen service designing and developing skills. This service requires Java 8 to run.

## BOM Structure

This project uses Spring Boots **Bill of Materials** structure for organising and version handling of dependencies. 

## Versioning

Standard semantic versioning is applied. More details [here](http://semver.org/).

## How to build project

This project can be build locally by using standard maven command for build:

    mvn clean install

## How to run

In order to run this service, you need to provide properties, either by placing **application.properties** in same folder as source, or by providing path to properties file when starting the service jar file. Command to start the service:

    java -jar crud-test-project.jar --spring.config.location=classpath:/application.properties

**spring.config.location** is required if properties file is not provider in classpath root, classpath /config package, current directory or in /config subdirectory.

Since this service uses Spring Boot, you can have several more alternatives in providing properties. More details [here](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html).

### Required properties

    server.port - port which service will listen
    spring.jpa.hibernate.ddl-auto - p
    spring.datasource.url - url to the database
    spring.datasource.username
    spring.datasource.password
    hibernate.dialect - hibernates dialect for provided database
    connection.driver_class - db connection driver

### Running in Development Mode
As this is a Spring Boot app, you can run it by simply running the file com.rest.example.Application.java. You will need to populate src/main/resources/application.properties with required properties.

## Functional Testing

Functional tests are in src/ft/java folder. In order to run them, you can use maven command: 
    
    mvn verify
    
For this, you need to popualte src/ft/resources/application.properties with required properties.
    
### Running FT in Development Mode
 
You can also run each functional test in IDE. In order to run it, you need to provide properties in src/ft/resources/application.properties.
# CRUD JWT Test project

This is a practice project based on Spring Platform Brussels-SR4. Purpose of it is just to sharpen developing skills.

## BOM Structure

This project uses Bill of Materials structure for organising dependencies and uses Spring Dependencies BOM for dependencies version handling. 

## Versioning

Standard semantic versioning is applied. More details [here](http://semver.org/).

## How to run

In order to run this service, you need to provide app properties.

### Required properties

### Running in Development Mode
As this is a Spring Boot app, you can run it by simply running the file Application.java. You will need to populate application.properties with required properties.

## Functional Testing

Functional tests are in src/ft/java folder. In order to run them, you can use maven command: mvn verify.

### Running FT in Development Mode
 
You can also run each functional test in IDE. In order to run it, you need to provide properties in src/ft/resources/application.properties.
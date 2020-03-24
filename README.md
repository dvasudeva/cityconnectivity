# cityconnectivity

This exercice is designed to find connectivity between 2 cities by using given mapping in CitiRoute.txt file 
under src\main\resources\input\. 

Technology stack is JAVA 1.8, Spring Boot and Maven as build tool.

This Solution accepts only 1 URI i.e. /connected with origin and destination as required parameter. origin and destination are not case-sensitive

Result:  If Connectivity found "YES" otherWise "NO"

Validation: 
  1. IF Required param are not in the URL, then Error (type=Bad Request, status=400).
  2. If Required param are there in the URL but value is missing for examle see Origin param in below url 
  http://localhost:8080/connected?origin=&destination=New%20York
  then "Missing Required Parammeter Origin or Destination"

JUNIT: JUNT Tests are added in src\test\java\com\mastercard\cityconnectivity where three separate test cases are added to validate asserts.

Swagger: Added Swagger for documentation and HTML UI to test through it.
http://localhost:8080/swagger-ui.html
http://localhost:8080/v2/api-docs

Build application: 
run command --> mvnw clean install
Result: Target folder should have JAr


Running application: Go to Target folder
Run Command -> java -jar <JarName>

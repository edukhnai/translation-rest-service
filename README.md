## TRANSLATION SERVICE

This application is a REST service based on Spring Boot that allows to translate text by using Yandex translator API.
### Requirements
Default Jdk version 1.8
#### Build 
Enter the following command in terminal from the project folder:
 
* Linux:`./gradlew build` 
* Windows: `gradlew.bat`
#### Start 
* Linux:`./gradlew bootRun` 
* Windows: `gradlew bootRun`
#### Example of request
Input parameters: 
text = "Alf, hide in the kitchen", initial language = English, required language = Deutsch

* Linux:
`curl -d '{"text" : "Alf, hide in the kitchen","from" : "en","to" : "de"}' -H "Content-Type: application/json" -X POST http://localhost:8080/translate
`
* Windows: 
`curl -d "{\"text\" : \"Alf, hide in the kitchen\",\"from\" : \"en\",\"to\" : \"de\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/translate
`

Application response: Alf, ausblenden in die Küche

#### Storing requests

All requests are stored in in-memory H2 database. When application is running, you can see all requests
in H2 console:
 1. Type http://localhost:8080/h2-console in browser address bar
 2. Set required parameters like here:
<p align="center">
  <img src="https://pp.userapi.com/c854324/v854324059/ef34/IGERoPosDLA.jpg">
</p>
 3. Type the following command to see all service requests:
<p align="center">
  <img src="https://pp.userapi.com/c847120/v847120381/1cef4f/wxXp-3MDCYA.jpg">
</p>

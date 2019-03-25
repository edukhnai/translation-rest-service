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





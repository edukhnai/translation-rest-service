## TRANSLATION SERVICE

This application is a REST service based on Spring Boot that allows to translate text by using Yandex translator API.
#### Build 
Enter the following command in terminal from the unzipped project folder:
`./gradlew build`
#### Start 
`gradle bootRun`
#### Example of request
Input parameters: 
text = "Alf, hide in the kitchen", initial language = English, required language = Deutsch

`curl -d '{"text" : "Alf, hide in the kitchen","from" : "en","to" : "de"}' -H "Content-Type: application/json" -X POST http://localhost:8080/translate
`

#### Result
Alf, ausblenden in die Küche
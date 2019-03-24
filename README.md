# TRANSLATION SERVICE

This application is a REST service based on Spring Boot that allows to translate text by using Yandex translator API.

#### Example of request

`POST /translate HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: 7893fdbe-dae8-43d7-a814-6500caf2dbc1
{
"text" : "alf, hide in the kitchen",
"from" : "en",
"to" : "de"
}`

#### Result

alf, ausblenden in die Küche


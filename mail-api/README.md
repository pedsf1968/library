# mail-api

[![pipeline status](https://gitlab.com/library3/batch/mail-api/badges/master/pipeline.svg)](https://gitlab.com/library3/batch/mail-api/-/commits/master)


## Object
Email API management that include a REST controller to send mails and query a Kafka server to get a mailing list. the properties files are in Git repository :
https://github.com/pedsf1968/properties-repository.git

Openapi 3.0 documentation generated at :
/v3/api-docs

Swagger-ui server at :
/swagger-ui.html

## Model Message
- firstName* : String
- lastName* : String
- from* : String
- to* : String
- subject* : String
- content* : String

## Mail controller
- POST /mail : send a Message



# config-server

[![pipeline status](https://gitlab.com/library3/config-server/badges/master/pipeline.svg)](https://gitlab.com/library3/config-server/-/commits/master)

Spring Boot property file server

##Object
Server that send after identification with login/password the properties file requested.
Get file from local or github repository. 

##Build with docker
- Use the command below to build the docker image :

docker build -t  config-server .

##Run container with docker
docker run -d -rm -p 2000:2000 -e jasypt.encryptor.password=$JASYPT_ENCRYPTOR_SECRET properties-server:1.0

- bind application on port 2000
- use your $JASYPT_ENCRYPTOR_SECRET envyronment property to decrypt sensible datas.

##use properties-server with GitHub properties repository
### Dockerfile
If you use a GitHub repository remove the line below in the Dockerfile.
COPY  ./properties-repository/*.properties /srv/properties-repository/

### application.properties
- set your GitHub URI 
spring.cloud.config.server.git.uri = https://github.com/*****/properties-repository.git

- remove the native location
spring.cloud.config.server.native.searchLocations=./properties-repository


##use properties-server with local properties directory
### Dockerfile
If you use local directory for the properties files discard comment from COPY line in the Dockerfile.
COPY  ./properties-repository/*.properties /srv/properties-repository/
 
### application.properties
- Set the path to your directory
spring.cloud.config.server.native.searchLocations=./properties-repository

- put git URI in comment
spring.cloud.config.server.git.uri = https://github.com/*****/properties-repository.git
 
 



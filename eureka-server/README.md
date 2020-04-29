# eureka-server

[![pipeline status](https://gitlab.com/library3/eureka-server/badges/master/pipeline.svg)](https://gitlab.com/library3/eureka-server/-/commits/master)

##Object
Euraka server

##Build with docker
- Use the command below to build the docker image :

docker build -t  eureka-server:1.0 .

##Run container with docker
docker run -d -rm -p 2100:2100 -e jasypt.encryptor.password=$JASYPT_ENCRYPTOR_SECRET eureka-server:1.0

- bind application on port 2100
- use your $JASYPT_ENCRYPTOR_SECRET envyronment property to decrypt sensible datas.

#
# batch : Dockerfile
#
# Build stage
#
FROM maven:3.5.2-jdk-8-alpine AS build
COPY src /srv/src
COPY pom.xml /srv
RUN mvn -f /srv/pom.xml clean package -DskipTests=true -Pdocker

#
# Package stage
#
FROM openjdk:8-jdk-alpine

COPY --from=build /srv/target/zuul-server.jar /srv/zuul-server.jar
WORKDIR /srv
RUN sh -c 'touch zuul-server.jar'
EXPOSE 2200

ENTRYPOINT ["java","-jar","zuul-server.jar"]

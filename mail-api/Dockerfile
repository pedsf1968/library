#
# mail-api : Dockerfile
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

COPY --from=build /srv/target/mail-api.jar /srv/mail-api.jar
COPY src/main/resources/images/background.png /images
COPY src/main/resources/images/library-banner.png /images
COPY src/main/resources/images/library-logo.png /images
COPY src/main/resources/images/logo-background.png /images

WORKDIR /srv
RUN sh -c 'touch mail-api.jar'
EXPOSE 5000

ENTRYPOINT ["java","-jar","mail-api.jar"]

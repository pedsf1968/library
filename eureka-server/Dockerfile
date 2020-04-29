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
COPY --from=build /srv/target/eureka-server.jar /srv/eureka-server.jar
WORKDIR /srv
RUN sh -c 'touch eureka-server.jar'
EXPOSE 2100

ENTRYPOINT ["java","-jar","eureka-server.jar"]

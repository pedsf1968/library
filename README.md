# library
Library project

# How to
## Starting with Docker
- Copy docker-compose.yml.
- Copy init.sql in the same directory.
- Execute : docker-compose up -d
- Open a browser in http://localhost:9000
- Connect with the user martin.dupont@gmail.com and password martin

You can change database content in ini.sql. 
Remove the volume project-library_pgdata to start with a new database.

## Starting development mode
- clone all microservices in the same directory.
- select development profile
- launch microservices in the way list and specify the port for user-api and library-api.   


## Starting local mode
- you must have a PostgreSQL server running.
- In user-api-local.properties and library -api-local.properties set :
  - spring.datasource.initialize=true
  - spring.jpa.hibernate.ddl-auto = create
- Clone all microservices GitLab repositories in the same directory.
- Select local profile
- Launch microservices in the way list and specify the port for user-api and library-api.
  -Dserver.port=7xxx for user-api
  -Dserver.port=8xxx for library-api 
- Execute the SQL scripts data.sql located in :
   - \library-api\src\main\resources\data.sql  
   - \user-api\src\main\resources\data.sql  
- Create the directory trees for media-api images :
  - media-api.upload.tmp=/TMP/
  - media-api.images.repository=/images/library/
  - media-api.book.images.repository=/images/library/book/
  - media-api.music.images.repository=/images/library/music/
  - media-api.video.images.repository=/images/library/video/
  - media-api.game.images.repository=/images/library/game/
  - media-api.user.images.repository=/images/library/user/
- If needed you can change it in media-api-development.properties
Image name is the media.id with jpg extension and must be placed in the right directory. 

# Microservices
## config-server
Cloud configuration server that you need to start first.
### Port
Use the port 2000

## eureka-server
Server to register all microservices used by ribbon for the load balancing
### Port
Use the port 2100

## zuul-server
Router for the application.
### Port
Use the port 2200

## media-api
REST controller for images distribution to the frontend.
### Port
Use the port 4000

## mail-api
REST controller for sending mails. It contain as service that collect messages from Karafka and mailing them.
### Port
Use the port 5000
### OpenAPI3 doc 
http://localhost:5000/swagger-ui.html
  
## batch
Execute once a day a request to know delayed borrowing, build message and send them to Karafka.

## user-api
RESTful controller for user management.
### Port
Use the port 7000
### OpenAPI3 doc 
http://localhost:7000/swagger-ui.html

## library-api
RESTful controller for business management.
### Port
Use the port 8000
### OpenAPI3 doc 
http://localhost:8000/swagger-ui.html

## web-api
The frontend API for the users.
### Port
Use the port 9000

## Database library
PostgreSQL:alpine 
Use the port 5432

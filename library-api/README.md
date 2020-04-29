# library-api

[![pipeline status](https://gitlab.com/library3/website/library-api/badges/master/pipeline.svg)](https://gitlab.com/library3/website/library-api/-/commits/master)


## Object
Rest controller for library management. the properties files are in Git repository :
https://github.com/pedsf1968/properties-repository.git

Openapi 3.0 documentation generated at :
/v3/api-docs

Swagger-ui server at :
/swagger-ui.html

## Profiles.
- Development : use an embeded H2 database.
- Production : query a Postgres database
- Docker : query a Postgres:alpine containerized database

# Models

## Model Address
- id : Integer,
- street1* : String,
- street2 : String,
- street3 : String,
- zipCode* : String,
- city* : String,
- country : String

## Model Book
- authorId : Integer (Person),
- editorId : Integer (Person),
- type : Enum(BookType),
- format : Enum (BookFormat),
- summary : String

## Model Borrowing
- id : Integer,
- userId : Integer (User),
- mediaId : String (Media),
- borrowingDate : Date,
- returnDate : Date,
- extended : Integer

## Model Game
- type : Enum (GameType),
- format : Enum (GameFormat),
- url : String

## Model Media
- id : String,
- mediaType : Enum (MediaType),
- title* : String,
- publicationDate : Date,
- returnDate : Date,
- quantityStock : Integer,
- quantityRemaining : Integer

## Model Music
- authorId : Integer (Person),
- composerId : Integer (Person),
- interpreterId : Integer (Person),
- duration : Time,
- type : Enum (MusicType),
- format : Enum (MusicFormat),
- url : String

## Model Person
- id* : Integer,
- firstName* : String,
- lastName* : String,
- birthDate : Date,
- url : String,
- photoUrl : String

## Model Role
- id : Integer
- name* : String
- users* : Set<User> 

## Model User
- id : Integer,
- firstName* : String,
- lastName* : String,
- email* : String,
- phone : String,
- address : String,
- photoLink : String,
- status : String,
- counter : Integer,
- roles : Set<Role>

## Model Video
- directorId* : Integer (Person),
- duration : Time,
- type : Enum (VideoType),
- format : Enum (VideoFormat),
- url : String

# Controllers

## Book controller
- DELETE /books/{bookId} : delete a Book by ID
- GET /books/{bookId} : get one Book by ID
- GET /books : get all Books
- GET /books/authors : get the list of all authors
- GET /books/editors : get the list of all editors
- GET /books/titles : get the list of all titles
- POST /books : create a Book
- POST /books/searches : get all Books with filter
- PUT /books/{bookId} : update a Book by ID

## Borrowing controller
- GET /borrowings/{borrowingId} : get one Borrowing by ID
- GET /borrowings : get all Borrowings
- GET /borrowings/delayed/{date} : get all delayed Borrowings
- GET /borrowings/user/{userId} : get borrowings by user ID

- POST /borrowings/{userId}/{MediaId} : User borrowing the Media
- POST /borrowings/restitute/{userId}/{MediaId} : restitute a Media
- POST /borrowings/searches : get all Borrowing with filter
   
## Game controller
- DELETE /games/{gameId} : delete a Game by ID
- GET /games/{gameId} : get one Game by ID
- GET /games : get all Games
- GET /games/titles : get the list of all titles
- POST /games : create a Game
- POST /games/searches : get all Games with filter
- PUT /games/{gameId} : update a Game by ID

## Media controller
- GET /medias/{mediaId} : get one Media by ID
- GET /medias : get all Medias
- POST /medias/searches : get all Medias with filter

## Music controller
- DELETE /musics/{musicId} : delete a Music by ID
- GET /musics/{musicId} : get one Music by ID
- GET /musics : get all Musics
- GET /musics/authors : get all Musics authors
- GET /musics/composers : get all Musics composers
- GET /musics/interpreters : get all Musics interpreters
- GET /musics/titles : get the list of all titles
- POST /musics : create a Music
- POST /musics/searches : get all Musics with filter
- PUT /musics/{musicId} : update a Music by ID
   
## Video controller
- DELETE /videos/{videoId} : delete a Video by ID
- GET /videos/{videoId} : get one Video by ID
- GET /videos : get all Videos
- GET /musics/directors : get all Video directors
- POST /videos : create a Video
- POST /videos/searches : get all Videos with filter
- PUT /videos/{videoId} : update a Video by ID

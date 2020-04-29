GOTO %1%
GOTO END

:ALL
:BATCH
@ECHO Building BATCH
cd F:\dev\javaee\PROJECT-Library\batch
docker build --rm -t batch:1.0 .
docker tag batch:1.0 pedsf1968/batch:1.0
if NOT '%2%'=='PUSH' GOTO ENDBATCH
docker push pedsf1968/batch:1.0
:ENDBATCH
if NOT '%1%'=='ALL' GOTO END

:CONFIG
@ECHO Building CONFIG
cd F:\dev\javaee\PROJECT-Library\config-server
docker build --rm -t config-server:1.0 .
docker tag config-server:1.0 pedsf1968/config-server:1.0
if NOT '%2%'=='PUSH' GOTO ENDCONFIG
docker push pedsf1968/config-server:1.0
:ENDCONFIG
if NOT '%1%'=='ALL' GOTO END

:EUREKA
@ECHO Building EUREKA
cd F:\dev\javaee\PROJECT-Library\eureka-server
docker build --rm -t eureka-server:1.0 .
docker tag  eureka-server:1.0  pedsf1968/eureka-server:1.0
if NOT '%2%'=='PUSH' GOTO ENDEUREKA
docker push pedsf1968/eureka-server:1.0
:ENDEUREKA
if NOT '%1%'=='ALL' GOTO END

:LIBRARY
@ECHO Building LIBRARY
cd F:\dev\javaee\PROJECT-Library\library-api
docker build --rm -t library-api:1.0 .
docker tag library-api:1.0 pedsf1968/library-api:1.0
if NOT '%2%'=='PUSH' GOTO ENDLIBRARY
docker push pedsf1968/library-api:1.0
:ENDLIBRARY
if NOT '%1%'=='ALL' GOTO END

:MAIL
@ECHO Building MAIL
cd F:\dev\javaee\PROJECT-Library\mail-api
docker build --rm -t mail-api:1.0 .
docker tag mail-api:1.0 pedsf1968/mail-api:1.0
if NOT '%2%'=='PUSH' GOTO ENDMAIL
docker push pedsf1968/mail-api:1.0
:ENDMAIL
if NOT '%1%'=='ALL' GOTO END

:MEDIA
@ECHO Building MEDIA
cd F:\dev\javaee\PROJECT-Library\media-api
docker build --rm -t media-api:1.0 .
docker tag media-api:1.0 pedsf1968/media-api:1.0
if NOT '%2%'=='PUSH' GOTO ENDMEDIA
docker push pedsf1968/media-api:1.0
:ENDMEDIA
if NOT '%1%'=='ALL' GOTO END

:USER
@ECHO Building USER
cd F:\dev\javaee\PROJECT-Library\user-api
docker build --rm -t user-api:1.0 .
docker tag user-api:1.0 pedsf1968/user-api:1.0
if NOT '%2%'=='PUSH' GOTO ENDUSER
docker push pedsf1968/user-api:1.0
:ENDUSER
if NOT '%1%'=='ALL' GOTO END

:WEB
@ECHO Building WEB
cd F:\dev\javaee\PROJECT-Library\web
docker build --rm -t web-api:1.0 .
docker tag web-api:1.0 pedsf1968/web-api:1.0
if NOT '%2%'=='PUSH' GOTO ENDWEB
docker push pedsf1968/web-api:1.0
:ENDWEB
if NOT '%1%'=='ALL' GOTO END

:ZUUL
@ECHO Building %1%
cd F:\dev\javaee\PROJECT-Library\zuul-server
docker build --rm -t zuul-server:1.0 .
docker tag  zuul-server:1.0  pedsf1968/zuul-server:1.0
if NOT '%2%'=='PUSH' GOTO ENDZUUL
docker push pedsf1968/zuul-server:1.0
:ENDZUUL

:END
cd F:\dev\javaee\PROJECT-Library
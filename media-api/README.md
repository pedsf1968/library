# media-api

[![pipeline status](https://gitlab.com/library3/media-api/badges/master/pipeline.svg)](https://gitlab.com/library3/media-api/-/commits/master)

## Object
Controller to upload and get images from directory. the properties files are in Git repository :
https://github.com/pedsf1968/properties-repository.git

## Profiles
- Development : use an local directory.
- Production : use an local directory
- Docker : use a mounted volume.

## Directories used
media-api.upload.tmp=/TMP/
media-api.images.repository=/images/
media-api.book.images.repository=/images/book/
media-api.music.images.repository=/images/music/
media-api.video.images.repository=/images/video/
media-api.game.images.repository=/images/game/
media-api.user.images.repository=/images/user/

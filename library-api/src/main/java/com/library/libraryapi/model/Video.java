package com.library.libraryapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Entity to manage Video Media Type
 *
 * directorId : identification of the director of the Video
 * duration : duration of the Video
 * type : Video type
 * format : Video format
 * image : image format types
 * audio : audio format types
 * audience : type of spectators
 * url : URL for video sample
 * actors : lists of the actors of the Video
 */
@Data
@Entity
@Table(name = "video")
@PrimaryKeyJoinColumn(name = "media_id")
public class Video extends Media implements Serializable {
   private static final int TYPE_MAX = 20;
   private static final int FORMAT_MAX = 20;
   private static final int AUDIO_FORMAT_MAX = 255;
   private static final int IMAGE_FORMAT_MAX = 255;
   private static final int PUBLIC_TYPE_MAX = 20;
   private static final int URL_MAX = 255; // default length
   private static final int SUMMARY_MAX = 2048;

   @NotNull
   @NotBlank
   @Column(name = "director_id")
   private Integer directorId;

   @Column(name = "duration")
   private Integer duration;

   @Column(name = "type", length = TYPE_MAX)
   @Enumerated(EnumType.STRING)
   private VideoType type;

   @Column(name = "format", length = FORMAT_MAX)
   @Enumerated(EnumType.STRING)
   private VideoFormat format;

   @Column(name = "image", length = IMAGE_FORMAT_MAX)
   private String image;

   @Column(name = "audio", length = AUDIO_FORMAT_MAX)
   private String audio;

   @Column(name = "audience", length = PUBLIC_TYPE_MAX)
   private String audience;

   @Column(name = "url", length = URL_MAX)
   private String url;

   @Column(name = "summary", length = SUMMARY_MAX)
   private String summary;

   @OneToMany()
   @JoinTable(name="video_actors",
         joinColumns = {@JoinColumn(name="video_id", referencedColumnName="media_id")},
         inverseJoinColumns = {@JoinColumn(name="actor_id", referencedColumnName="id")})
   private Set<Person> actors;

}

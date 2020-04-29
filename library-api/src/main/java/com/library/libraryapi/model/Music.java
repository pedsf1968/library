package com.library.libraryapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entity to manage Music Media Type
 *
 * authorId : identification of the author of the Music
 * composerId : identification of the composer of the Music
 * interpreterId : identification of the interpreter of the Music
 * duration : duration of the Music
 * type : Music type
 * format : Music format
 */
@Data
@Entity
@Table(name = "music")
@PrimaryKeyJoinColumn(name = "media_id")
public class Music extends Media  implements Serializable {
   private static final int TYPE_MAX = 20;
   private static final int FORMAT_MAX = 20;
   private static final int PEGI_MAX = 4;
   private static final int URL_MAX = 255; // default length

   @Column(name = "author_id")
   private Integer authorId;

   @Column(name = "composer_id")
   private Integer composerId;

   @NotNull
   @Column(name = "interpreter_id")
   private Integer interpreterId;

   @Column(name = "duration")
   private Integer duration;

   @Column(name = "type", length = TYPE_MAX)
   @Enumerated(EnumType.STRING)
   private MusicType type;

   @Column(name = "format", length = FORMAT_MAX)
   @Enumerated(EnumType.STRING)
   private MusicFormat format;

   @Column(name = "url", length = URL_MAX)
   private String url;
}

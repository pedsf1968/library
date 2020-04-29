package com.library.libraryapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entity to manage Game Media Type
 *
 * type : Game type
 * format : Game format
 */
@Data
@Entity
@Table(name = "game")
@PrimaryKeyJoinColumn(name = "media_id")
public class Game extends Media implements Serializable {
   private static final int TYPE_MAX = 20;
   private static final int FORMAT_MAX = 20;
   private static final int PEGI_MAX = 4;
   private static final int URL_MAX = 255; // default length
   private static final int SUMMARY_MAX = 2048;

   @NotNull
   @Column(name = "editor_id")
   private Integer editorId;

   @Column(name = "type", length = TYPE_MAX)
   @Enumerated(EnumType.STRING)
   private GameType type;

   @Column(name = "format", length = FORMAT_MAX)
   @Enumerated(EnumType.STRING)
   private GameFormat format;

   @Column(name = "pegi", length = PEGI_MAX)
   private String pegi;

   @Column(name = "url", length = URL_MAX)
   private String url;

   @Column(name = "summary", length = SUMMARY_MAX)
   private String summary;
}

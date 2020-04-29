package com.library.web.dto.business;

import com.library.web.dto.MediaType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * Data Transfert Object to manage Game
 *
 * id : identification of the Game
 * ean : EAN code
 * mediaType : type of the Media (GAME)
 * title : title of the Media
 * publicationDate : is the date when the Media is published
 * returnDate : the date of the next expected return (null if all Media are available in stock)
 * stock : total of this Media owned by the library
 * remaining : remaining Media in the library to be borrowed
 * weight : weight of the Media
 * length : length of the Media
 * width : width of the Media
 * height : height of the Media
 *
 * editor : editor of the Game
 * type : Game type
 * format : Game format
 * pegi : PEGI notation for games
 * summary : Game summary
 * url : link to the Game trailer
 */
@Data
public class GameDTO {
   private static final int TITLE_MIN = 1;
   private static final int TITLE_MAX = 50;
   private static final int EAN_MAX = 20;
   private static final int TYPE_MAX = 20;
   private static final int FORMAT_MAX = 20;
   private static final int PEGI_MAX = 4;
   private static final int URL_MAX = 255; // default length
   private static final int SUMMARY_MAX = 2048;

   private static final String ERROR_MESSAGE_BETWEEN = "Length should be between : ";
   private static final String ERROR_MESSAGE_LESS = "Length should less than : ";

   // Media attributes
   private Integer id;
   @Size(max = EAN_MAX, message = ERROR_MESSAGE_LESS + EAN_MAX)
   private String ean;
   private String mediaType = MediaType.GAME.toString();

   @NotNull
   @Size(min = TITLE_MIN, max = TITLE_MAX,
         message = ERROR_MESSAGE_BETWEEN + TITLE_MIN + " and " + TITLE_MAX  + " !")
   private String title;

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private Date publicationDate;

   private Integer weight;
   private Integer length;
   private Integer width;
   private Integer height;

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private Date returnDate;
   private Integer stock;
   private Integer remaining;

   // game attributes
   private PersonDTO editor;
   @Size(max = TYPE_MAX, message = ERROR_MESSAGE_LESS + TYPE_MAX)
   private String type;
   @Size(max = FORMAT_MAX, message = ERROR_MESSAGE_LESS + FORMAT_MAX)
   private String format;
   @Size(max = PEGI_MAX, message = ERROR_MESSAGE_LESS + PEGI_MAX)
   private String pegi;
   @Size(max = URL_MAX, message = ERROR_MESSAGE_LESS + URL_MAX)
   private String url;
   @Size(max = SUMMARY_MAX, message = ERROR_MESSAGE_LESS + SUMMARY_MAX)
   private String summary;

   public GameDTO() {
      this.mediaType = MediaType.GAME.toString();
   }
}

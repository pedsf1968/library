package com.library.libraryapi.dto.business;

import com.library.libraryapi.model.MediaType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Data Transfert Object to manage Video
 *
 * ean : EAN code
 * mediaType : type of the Media (VIDEO)
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
 * director : director of the Video
 * duration : duration of the Video
 * type : Video type
 * format : Video format
 * url : link to Video trailer
 * actors : lists of the actors of the Video
 */
@Data
public class VideoDTO implements Serializable {
   private static final int TITLE_MIN = 1;
   private static final int TITLE_MAX = 50;
   private static final int EAN_MAX = 20;
   private static final int TYPE_MAX = 20;
   private static final int FORMAT_MAX = 20;
   private static final int AUDIO_FORMAT_MAX = 255;
   private static final int IMAGE_FORMAT_MAX = 255;
   private static final int PUBLIC_TYPE_MAX = 20;
   private static final int URL_MAX = 255; // default length
   private static final int SUMMARY_MAX = 2048;

   private static final String ERROR_MESSAGE_BETWEEN = "Length should be between : ";
   private static final String ERROR_MESSAGE_LESS = "Length should less than : ";

   // Media attributes
   private Integer id;
   @Size(max = EAN_MAX, message = ERROR_MESSAGE_LESS + EAN_MAX)
   private String ean;
   private String mediaType = MediaType.VIDEO.toString();

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

   // Video attributes
   @NotNull
   private PersonDTO director;
   private Integer duration;
   @Size(max = TYPE_MAX, message = ERROR_MESSAGE_LESS + TYPE_MAX)
   private String type;
   @Size(max = FORMAT_MAX, message = ERROR_MESSAGE_LESS + FORMAT_MAX)
   private String format;
   @Size(max = IMAGE_FORMAT_MAX, message = ERROR_MESSAGE_LESS + IMAGE_FORMAT_MAX)
   private String image;
   @Size(max = AUDIO_FORMAT_MAX, message = ERROR_MESSAGE_LESS + AUDIO_FORMAT_MAX)
   private String audio;
   @Size(max = PUBLIC_TYPE_MAX, message = ERROR_MESSAGE_LESS + PUBLIC_TYPE_MAX)
   private String audience;
   @Size(max = SUMMARY_MAX, message = ERROR_MESSAGE_LESS + SUMMARY_MAX)
   private String summary;
   @Size(max = URL_MAX, message = ERROR_MESSAGE_LESS + URL_MAX)
   private String url;
   private List<PersonDTO> actors;

   public VideoDTO() {
      this.mediaType = MediaType.VIDEO.toString();
   }
}

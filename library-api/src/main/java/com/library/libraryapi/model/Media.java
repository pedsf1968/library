package com.library.libraryapi.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

/**
 * Entity to manage common part of all Media
 *
 * id : identification of the Media
 * ean : ean code like ISBN for BOOKS
 * mediaType : type of the Media (BOOK,MUSIC,VIDEO,GAME...)
 * title : title of the book, movie, music, song, game
 * publicationDate : is the date when the Media is published
 * returnDate : the date of the next expected return (null if all Media are available in stock)
 * quantityStock : total of this Media owned by the library
 * quantityRemaining : remaining Media in the library to be borrowed
 */
@Data
@Entity
@Table(name = "media")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media implements Serializable {
   private static final int TITLE_MIN = 1;
   private static final int TITLE_MAX = 50;
   private static final int EAN_MAX = 20;
   private static final int MEDIA_TYPE_MAX = 10;

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Integer id;

   @NotNull
   @Column(name = "ean", length = EAN_MAX)
   private String ean;

   @NotNull
   @Column(name = "media_type", length = MEDIA_TYPE_MAX)
   @Enumerated(EnumType.STRING)
   protected MediaType mediaType;

   @NotNull
   @NotBlank
   @Size(min = TITLE_MIN, max = TITLE_MAX)
   @Column(name = "title", length = TITLE_MAX)
   private String title;

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   @Column(name = "publication_date")
   private Date publicationDate;

   // weight and dimensions for transport informations
   @Column(name = "weight")
   private Integer weight;

   @Column(name = "length")
   private Integer length;

   @Column(name = "width")
   private Integer width;

   @Column(name = "height")
   private Integer height;

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   @Column(name = "return_date")
   private Date returnDate;

   @Column(name = "stock")
   private Integer stock;

   @Column(name = "remaining")
   private Integer remaining;
}

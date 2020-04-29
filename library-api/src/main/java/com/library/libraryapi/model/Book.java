package com.library.libraryapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entity to manage Book Media Type
 *
 * authorId : identification of the writer of the Book
 * editorId : identification of the editor of the Book
 * type : Book type
 * format : Book format
 * summary : Book summary
 */
@Data
@Entity
@Table(name = "book")
@PrimaryKeyJoinColumn(name = "media_id")
public class Book extends Media implements Serializable {
   private static final int ISBN_MAX = 20;
   private static final int TYPE_MAX = 20;
   private static final int FORMAT_MAX = 20;
   private static final int SUMMARY_MAX = 2048;

   @NotNull
   @Column(name = "isbn", length = ISBN_MAX)
   private String isbn;

   @NotNull
   @Column(name = "author_id")
   private Integer authorId;

   @NotNull
   @Column(name = "editor_id")
   private Integer editorId;

   @Column(name = "type", length = TYPE_MAX)
   @Enumerated(EnumType.STRING)
   private BookType type;

   @Column(name = "format", length = FORMAT_MAX)
   @Enumerated(EnumType.STRING)
   private BookFormat format;

   @Column(name = "pages")
   private Integer pages;

   @Column(name = "summary", length = SUMMARY_MAX)
   private String summary;
}

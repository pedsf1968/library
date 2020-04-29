package com.library.libraryapi.dto.global;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {
   private static final int STREET_MAX = 50;
   private static final int ZIP_MIN = 5;
   private static final int ZIP_MAX = 6;
   private static final int CITY_MAX = 50;
   private static final int COUNTRY_MAX = 50;

   private static final String ERROR_MESSAGE_BETWEEN = "Length should be between : ";
   private static final String ERROR_MESSAGE_LESS = "Length should less than : ";

   private Integer id;

   @NotNull
   @Size(max = STREET_MAX, message = ERROR_MESSAGE_LESS + STREET_MAX + " !")
   private String street1;

   @Size(max = STREET_MAX, message = ERROR_MESSAGE_LESS + STREET_MAX + " !")
   private String street2;

   @Size(max = STREET_MAX, message = ERROR_MESSAGE_LESS + STREET_MAX + " !")
   private String street3;

   @NotNull
   @Size(min = ZIP_MIN, max = ZIP_MAX, message = ERROR_MESSAGE_BETWEEN + ZIP_MIN + " AND " + ZIP_MAX + " !")
   private String zipCode;
   
   @NotNull
   @Size(max = CITY_MAX, message = ERROR_MESSAGE_LESS + CITY_MAX + " !")
   private String city;

   @Size(max = COUNTRY_MAX, message = ERROR_MESSAGE_LESS + COUNTRY_MAX + " !")
   private String country;
}

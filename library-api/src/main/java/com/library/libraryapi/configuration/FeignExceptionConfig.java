package com.library.libraryapi.configuration;


import com.library.libraryapi.exceptions.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignExceptionConfig {

   @Bean
   CustomErrorDecoder myCustomErrorDecoder(){
      return new CustomErrorDecoder();
   }
}

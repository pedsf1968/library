package com.library.mailapi.controller;


import com.library.mailapi.dto.MessageDTO;

import com.library.mailapi.exception.BadRequestException;
import com.library.mailapi.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class MailController {
   private final EmailService emailService;

   public MailController(EmailService emailService) {
      this.emailService = emailService;
   }


   @PostMapping("/mails")
   public ResponseEntity<Void> sendMail(@RequestBody MessageDTO messageDTO, Locale locale) {

      try {
         emailService.sendMailAsynch(messageDTO, locale);
         return ResponseEntity.ok().build();
      } catch (BadRequestException ex) {
         // log exception first, then return Bad Request (400)
         log.error(ex.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
   }

}

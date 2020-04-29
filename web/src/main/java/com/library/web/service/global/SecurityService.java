package com.library.web.service.global;

public interface SecurityService {
   String findLoggedInUsername();
   void autoLogin(String email, String motDePasse);
}
//package com.example.schoolapi.controller.auth;
//
//import com.example.schoolapi.dto.LoginDto;
//import com.example.schoolapi.service.auth.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class LoginController {
//
//  @Autowired
//  LoginService loginService;
//
//  @PostMapping("/login")
//  public String login(@RequestBody LoginDto loginDto) {
//    try {
//      String token = loginService.login(loginDto);
//      return "Token: "+token;
//    } catch(Exception e) {
//      return "Email or password is incorrect";
//    }
//  }
//
//}

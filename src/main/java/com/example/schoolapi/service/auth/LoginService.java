//package com.example.schoolapi.service.auth;
//
//import com.example.schoolapi.dto.LoginDto;
//import com.example.schoolapi.model.User;
//import com.example.schoolapi.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService {
//
//  private final UserRepository userRepository;
//
//  @Autowired
//  public LoginService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }
//
//  public String login(LoginDto loginDto) throws Exception {
//    if(userRepository.findByEmail(loginDto.getEmail()).isPresent()) {
//      User user = userRepository.findByEmail(loginDto.getEmail()).get();
//      if(user.getPassword().equals(loginDto.getPassword())) {
//        return loginDto.getEmail()+loginDto.getPassword();
//      }
//      throw new Exception();
//    }
//    throw new Exception();
//  }
//}

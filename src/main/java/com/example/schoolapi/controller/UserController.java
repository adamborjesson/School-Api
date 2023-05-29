package com.example.schoolapi.controller;

import com.example.schoolapi.model.User;
import com.example.schoolapi.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/getall")
  public ResponseEntity<List<User>> getUsers() {

    return ResponseEntity.ok().body(userService.getUsers());
  }
}

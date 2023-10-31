package com.example.schoolapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.schoolapi.dto.UserDto;
import com.example.schoolapi.model.User;
import com.example.schoolapi.repository.UserRepository;
import com.example.schoolapi.service.UserService;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerTest {

  static UserService userService;
  @Autowired
  UserRepository userRepository;


  @BeforeEach
  void setUp() {
    userService = new UserService(userRepository);
  }


  @Test
  void getUsers() {
    ResponseEntity<List<User>> responseEntity = ResponseEntity.ok().body(userService.getUsers());
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }
}
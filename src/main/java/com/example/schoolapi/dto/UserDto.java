package com.example.schoolapi.dto;

import lombok.Value;

@Value
public class UserDto {
  Long id;
  String email;
  String role;
}

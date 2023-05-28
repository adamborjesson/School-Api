package com.example.schoolapi.dto;


import java.util.List;
import lombok.Value;

@Value
public class StudentDto {
  String name;
  String email;
  String password;
}

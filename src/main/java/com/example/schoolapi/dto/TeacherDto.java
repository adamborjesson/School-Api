package com.example.schoolapi.dto;

import java.util.List;
import lombok.Value;

@Value
public class TeacherDto {
  Long id;
  Long userId;
  String name;
  String email;
  List<Long> educationId;
  List<Long> studentId;
}

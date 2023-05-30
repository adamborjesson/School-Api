package com.example.schoolapi.dto;


import java.util.List;
import lombok.Value;

@Value
public class StudentDto {
  Long id;
  Long UserId;
  String name;
  Long educationId;
  List<Long> teacherIds;

}

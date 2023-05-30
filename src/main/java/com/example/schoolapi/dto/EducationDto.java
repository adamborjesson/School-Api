package com.example.schoolapi.dto;

import java.util.List;
import lombok.Value;

@Value
public class EducationDto {
  Long id;
  String name;
  List<Long> teacherId;
  List<Long> studentId;
}

package com.example.schoolapi.dto;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class LessonDto {
  Long educationId;
  LocalDateTime date;
}

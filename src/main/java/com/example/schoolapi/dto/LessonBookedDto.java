package com.example.schoolapi.dto;

import com.example.schoolapi.model.lesson.ELesson;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class LessonBookedDto {
  Long id;
  Long educationId;
  LocalDateTime date;
  ELesson state;
}

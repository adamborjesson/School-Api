package com.example.schoolapi.model.lesson;

import com.example.schoolapi.dto.LessonBookedDto;
import com.example.schoolapi.dto.LessonDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long educationId;
  private LocalDateTime date;
  @Enumerated(EnumType.STRING)
  private ELesson state;



  public Lesson(LessonDto classDto) {
    this.educationId = classDto.getEducationId();
    this.date = classDto.getDate();
    this.state = ELesson.BOOKED;
  }

  public LessonBookedDto getFullDto() {
    return new LessonBookedDto(
        this.getId(),
        this.getEducationId(),
        this.getDate(),
        this.getState());
  }
}

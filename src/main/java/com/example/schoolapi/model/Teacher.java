package com.example.schoolapi.model;

import com.example.schoolapi.dto.TeacherDto;
import com.example.schoolapi.dto.TeacherSignUpDto;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long userId;
  private String name;
  private String email;
  @ElementCollection
  private List<Long> educationId;
  @ElementCollection
  private List<Long> studentId;

  public Teacher(TeacherSignUpDto teacherSignUpDto) {
    this.name = teacherSignUpDto.getName();
    this.email = teacherSignUpDto.getEmail();
  }
  public TeacherDto getFullDto() {
    return new TeacherDto(
        this.getId(),
        this.getUserId(),
        this.getName(),
        this.getEmail(),
        this.getEducationId(),
        this.getStudentId());
  }
}

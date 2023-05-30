package com.example.schoolapi.model;

import com.example.schoolapi.dto.StudentDto;
import com.example.schoolapi.dto.StudentSignUpDto;
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
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long userId;
  private String name;
  private String email;
  private Long educationId;
  @ElementCollection
  private List<Long> teacherIds;


  public Student(StudentSignUpDto studentSignUpDto) {
    this.name = studentSignUpDto.getName();
    this.email = studentSignUpDto.getEmail();
  }

  public StudentDto getFullDTO() {
    return new StudentDto(
        this.getId(),
        this.getUserId(),
        this.getName(),
        this.getEducationId(),
        this.getTeacherIds());
  }
}

package com.example.schoolapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.schoolapi.dto.EducationDto;
import com.example.schoolapi.dto.TeacherDto;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.TeacherRepository;
import com.example.schoolapi.service.TeacherService;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class TeacherControllerTest {
  @Autowired
  TeacherRepository teacherRepository;
  @Autowired
  EducationRepository educationRepository;
  static TeacherService teacherService;
  @BeforeEach
  void setUp() {
    teacherService = new TeacherService(teacherRepository, educationRepository);
  }


  @Test
  void addEducation() {
    Teacher teacher = new Teacher();
    teacher.setEducationId(new ArrayList<>());
    teacher.setStudentId(new ArrayList<>());
    teacherRepository.save(teacher);
    Education education = new Education();
    education.setTeacherId(new ArrayList<>());
    education.setStudentId(new ArrayList<>());
    educationRepository.save(education);
    ResponseEntity<TeacherDto> responseEntity = ResponseEntity.ok().body(teacherService.addEducation(teacher.getId(), education.getId()));
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  void getTeacher() {
    Teacher teacher = teacherRepository.save(new Teacher());
    ResponseEntity<TeacherDto> responseEntity = ResponseEntity.ok().body(teacherService.getTeacher(teacher.getId()));
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }
}
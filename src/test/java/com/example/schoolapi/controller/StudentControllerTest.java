package com.example.schoolapi.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.schoolapi.dto.StudentDto;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Student;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.StudentRepository;
import com.example.schoolapi.repository.TeacherRepository;
import com.example.schoolapi.repository.UserRepository;
import com.example.schoolapi.service.StudentService;
import java.util.ArrayList;
import java.util.List;
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
class StudentControllerTest {
static StudentService studentService;
  @Autowired
  StudentRepository studentRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  EducationRepository educationRepository;
  @Autowired
  TeacherRepository teacherRepository;
  @BeforeEach
  void setUp() {
    studentService = new StudentService(studentRepository, userRepository, educationRepository, teacherRepository);
  }

  @Test
  void getStudent() {
    Student student = studentRepository.save(new Student());
    ResponseEntity<StudentDto> responseEntity = ResponseEntity.ok().body(studentService.getStudent(student.getId()));
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  void addEducation() {
    Student student = studentRepository.save(new Student());
    Teacher teacher = new Teacher();
    List<Long> studentIds = new ArrayList<>();
    studentIds.add(student.getId());
    teacher.setStudentId(studentIds);
    teacherRepository.save(teacher);
    Education education = new Education();
    List<Long> teacherIds = new ArrayList<>();
    teacherIds.add(teacher.getId());
    education.setTeacherId(teacherIds);
    education.setStudentId(studentIds);
    educationRepository.save(education);
    ResponseEntity<StudentDto> responseEntity = ResponseEntity.ok().body(studentService.addEducation(student.getId(), education.getId()));
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }
}
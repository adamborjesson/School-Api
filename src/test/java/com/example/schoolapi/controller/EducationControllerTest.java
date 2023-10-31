package com.example.schoolapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.schoolapi.dto.LessonBookedDto;
import com.example.schoolapi.dto.LessonDto;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Student;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.model.lesson.ELesson;
import com.example.schoolapi.model.lesson.Lesson;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.LessonRepository;
import com.example.schoolapi.repository.StudentRepository;
import com.example.schoolapi.repository.TeacherRepository;
import com.example.schoolapi.service.EducationService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class EducationControllerTest {
  static EducationService educationService;

  @Autowired
  TestEntityManager testEntityManager;

  @Autowired
  EducationRepository educationRepository;
  @Autowired
  StudentRepository studentRepository;
  @Autowired
  TeacherRepository teacherRepository;
  @Autowired
  LessonRepository lessonRepository;

  @BeforeEach
  void setUp() {
    educationService = new EducationService(educationRepository, studentRepository, teacherRepository, lessonRepository);
  }


  @Test
  void terminateStudent() {
    Student student = new Student();
    studentRepository.save(student);
    Education education = new Education();
    List<Long> studentId = new ArrayList<>();
    studentId.add(student.getId());
    education.setStudentId(studentId);
    educationRepository.save(education);
    ResponseEntity<String> message = ResponseEntity.ok().body(educationService.terminateStudent(education.getId(), student.getId()));
    assertEquals(HttpStatus.OK, message.getStatusCode());
  }

  @Test
  void getAllEducations() {
    ResponseEntity<List<Education>> getAllEducations = ResponseEntity.ok().body(educationService.getAllEducations());
    assertEquals(HttpStatus.OK, getAllEducations.getStatusCode());
  }

  @Test
  void bookLesson() {
    Teacher teacher = new Teacher();
    List<Long> teacherId = new ArrayList<>();
    teacherId.add(1L);
    teacher.setEducationId(teacherId);
    teacherRepository.save(teacher);
    Date date = new Date();
    LocalDateTime localDateTime = LocalDateTime.now();
    ResponseEntity<LessonBookedDto> lessonBookedDto = ResponseEntity.ok().body(educationService.bookLesson(teacher.getId(), new LessonDto(1L, localDateTime)));
    //Lesson lesson = lessonRepository.findById(lessonBookedDto.getId()).get();
    //assertEquals(1L, lesson.getEducationId());
    assertEquals(HttpStatus.OK, lessonBookedDto.getStatusCode());

  }

  @Test
  void startLesson() {
    Lesson lesson = new Lesson();
    lesson.setState(ELesson.BOOKED);
    lessonRepository.save(lesson);
    ResponseEntity<LessonBookedDto> startLesson = ResponseEntity.ok().body(educationService.startLesson(lesson.getId()));
    assertEquals(HttpStatus.OK, startLesson.getStatusCode());
  }

  @Test
  void finishLesson() {
    Lesson lesson = new Lesson();
    lesson.setState(ELesson.STARTED);
    lessonRepository.save(lesson);
    ResponseEntity<LessonBookedDto> finishLesson = ResponseEntity.ok().body(educationService.finishLesson(lesson.getId()));
    assertEquals(HttpStatus.OK, finishLesson.getStatusCode());
  }
}
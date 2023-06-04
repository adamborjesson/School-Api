package com.example.schoolapi.controller;

import com.example.schoolapi.dto.LessonBookedDto;
import com.example.schoolapi.dto.LessonDto;
import com.example.schoolapi.dto.EducationDto;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.service.EducationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/educations")
public class EducationController {

  private final EducationService educationService;

  public EducationController(EducationService educationService) {
    this.educationService = educationService;
  }

  @PutMapping("/terminate/student/{educationId}/{studentId}")
  public ResponseEntity<String> terminateStudent(@PathVariable Long educationId, @PathVariable Long studentId) {
      educationService.terminateStudent(educationId, studentId);

    return ResponseEntity.ok().body("Student is terminated");
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<EducationDto>> getAllEducations() {
    return ResponseEntity.ok().body(educationService.getAllEducations()
        .stream()
        .map(Education::getFullDto)
        .collect(Collectors.toList()));
  }

  @PostMapping("/book/lesson/{teacherId}")
  public ResponseEntity<LessonBookedDto> bookLesson(@PathVariable Long teacherId, @RequestBody LessonDto classDto) {
    return ResponseEntity.ok().body(educationService.bookLesson(teacherId, classDto));
  }

  @PutMapping("/start/lesson/{lessonId}")
  public ResponseEntity<LessonBookedDto> startLesson(@PathVariable Long lessonId) {
    return ResponseEntity.ok().body(educationService.startLesson(lessonId));
  }

  @PutMapping("/finish/lesson/{lessonId}")
  public ResponseEntity<LessonBookedDto> finishLesson(@PathVariable Long lessonId) {
    return ResponseEntity.ok().body(educationService.finishLesson(lessonId));
  }

}

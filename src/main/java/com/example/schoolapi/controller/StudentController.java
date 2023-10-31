package com.example.schoolapi.controller;

import com.example.schoolapi.dto.StudentDto;
import com.example.schoolapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {


    this.studentService = studentService;
  }



  @GetMapping("/get/{id}")
  public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {

      return ResponseEntity.ok().body(studentService.getStudent(id));
    
  }
  @PutMapping("/add/education/{studentId}/{educationId}")
  public ResponseEntity<StudentDto> addEducation(@PathVariable Long studentId, @PathVariable Long educationId) {
    try {
      return ResponseEntity.ok().body(studentService.addEducation(studentId, educationId));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education or student could not be found");
    }
  }

}

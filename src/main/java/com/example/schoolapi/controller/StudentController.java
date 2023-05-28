package com.example.schoolapi.controller;

import com.example.schoolapi.model.Student;
import com.example.schoolapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {

    this.studentService = studentService;
  }



  @GetMapping("/get/{id}")
  public Student getStudent(@PathVariable Long id) {

    return studentService.getStudent(id);
  }
  @PutMapping("/add/education/{id}/{educationId}")
  public Student addEducation(@PathVariable Long id, @PathVariable Long educationId) {
    return studentService.addEducation(id, educationId);
  }
}

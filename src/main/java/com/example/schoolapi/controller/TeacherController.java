package com.example.schoolapi.controller;

import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

  private final TeacherService teacherService;

  @Autowired
  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @PutMapping("/add/education/{teacherId}/{educationId}")
  public Teacher addEducation(@PathVariable Long teacherId, @PathVariable Long educationId) {
    return teacherService.addEducation(teacherId, educationId);
  }
}

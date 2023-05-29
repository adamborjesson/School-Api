package com.example.schoolapi.controller;

import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

  private final TeacherService teacherService;

  @Autowired
  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @PutMapping("/add/education/{teacherId}/{educationId}")
  public ResponseEntity<Teacher> addEducation(@PathVariable Long teacherId, @PathVariable Long educationId) {
    try {
      return ResponseEntity.ok().body(teacherService.addEducation(teacherId, educationId));
    }catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education or teacher could not be found");
    }

  }

  @GetMapping("/get/{teacherId}")
  public ResponseEntity<Teacher> getTeacher(@PathVariable Long teacherId) {
    try {
      return ResponseEntity.ok().body(teacherService.getTeacher(teacherId));
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher could not be found");
    }
  }

}

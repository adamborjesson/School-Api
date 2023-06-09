package com.example.schoolapi.controller.auth;

import com.example.schoolapi.dto.StudentDto;
import com.example.schoolapi.dto.StudentSignUpDto;
import com.example.schoolapi.dto.TeacherDto;
import com.example.schoolapi.dto.TeacherSignUpDto;
import com.example.schoolapi.model.Student;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.service.auth.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

  private final RegistrationService registrationService;

  @Autowired
  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

@PostMapping("/register/student")
  public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentSignUpDto studentSignUpDto) {
    return ResponseEntity.ok().body(registrationService.registerStudent(studentSignUpDto));

  }

@PostMapping("register/teacher")
  public ResponseEntity<TeacherDto> registerTeacher(@RequestBody TeacherSignUpDto teacherSignUpDto) {
    return ResponseEntity.ok().body(registrationService.registerTeacher(teacherSignUpDto));
}
}

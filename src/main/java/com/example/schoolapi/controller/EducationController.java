package com.example.schoolapi.controller;

import com.example.schoolapi.dto.EducationDto;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.service.EducationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<String> terminateStudent(@PathVariable Long educationId, @PathVariable Long studentId)
      throws Exception {
    String message = null;
    try {
      educationService.terminateStudent(educationId, studentId);
      message =  "Student is terminated";
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Education or student could not be found or "
          + "student dosen't belong to this education");
    }
    return ResponseEntity.ok().body(message);
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<EducationDto>> getAllEducations() {
    return ResponseEntity.ok().body(educationService.getAllEducations()
        .stream()
        .map(Education::getFullDto)
        .collect(Collectors.toList()));
  }
}

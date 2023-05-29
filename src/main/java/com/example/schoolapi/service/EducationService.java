package com.example.schoolapi.service;

import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Student;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.StudentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
  
  private final EducationRepository educationRepository;
  private final StudentRepository studentRepository;
  
  public EducationService(EducationRepository educationRepository, StudentRepository studentRepository) {
    this.educationRepository = educationRepository;
    this.studentRepository = studentRepository;
  }

  public void terminateStudent(Long educationId, Long studentId) {

    Education education = educationRepository.findByIdAndStudentId(educationId, studentId).get();
    education.getStudentId().remove(studentId);
    educationRepository.save(education);

  }

  public List<Education> getAllEducations() {
    return educationRepository.findAll();
  }
}

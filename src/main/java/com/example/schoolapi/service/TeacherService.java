package com.example.schoolapi.service;

import com.example.schoolapi.dto.TeacherDto;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

  private final TeacherRepository teacherRepository;
  private final EducationRepository educationRepository;

  @Autowired
  public TeacherService(TeacherRepository teacherRepository, EducationRepository educationRepository) {
    this.teacherRepository = teacherRepository;
    this.educationRepository = educationRepository;
  }


  public TeacherDto addEducation(Long teacherId, Long educationId) {
    Teacher teacher = teacherRepository.findById(teacherId).get();
    teacher.getEducationId().add(educationId);
    Education education = educationRepository.findById(educationId).get();
    education.getTeacherId().add(teacherId);
    educationRepository.save(education);
    teacher.getStudentId().addAll(education.getStudentId());
    return teacherRepository.save(teacher).getFullDto();
  }

  public TeacherDto getTeacher(Long teacherId) {
    return teacherRepository.findById(teacherId).get().getFullDto();
  }
}

package com.example.schoolapi.service;

import com.example.schoolapi.dto.StudentDto;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Student;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.StudentRepository;
import com.example.schoolapi.repository.TeacherRepository;
import com.example.schoolapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final UserRepository userRepository;
  private final EducationRepository educationRepository;
  private final TeacherRepository teacherRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository,
      UserRepository userRepository,
      EducationRepository educationRepository,
      TeacherRepository teacherRepository) {
    this.studentRepository = studentRepository;
    this.userRepository = userRepository;
    this.educationRepository = educationRepository;
    this.teacherRepository = teacherRepository;
  }

//  public Student createStudent(StudentDto studentDto) {
//    Student student = new Student(studentDto);
//    return studentRepository.save(student);
//  }

  public StudentDto getStudent(Long id) {
    if (studentRepository.findById(id).isPresent()) {
      return studentRepository.findById(id).get().getFullDTO();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student could not be found");
  }

  public StudentDto addEducation(Long id, Long educationId) {
    Student student = studentRepository.findById(id).get();
    Education education = educationRepository.findById(educationId).get();
    for (Long teacherId : education.getTeacherId()) {
      Teacher teacher = teacherRepository.findById(teacherId).get();
      if (!teacher.getStudentId().contains(id)) {
        teacher.getStudentId().add(id);
        teacherRepository.save(teacher);
        student.getTeacherIds().addAll(education.getTeacherId());
      }
      if (student.getEducationId() == null) {
        student.setEducationId(educationId);
        education.getStudentId().add(id);
      }
      educationRepository.save(education);
    }
    return studentRepository.save(student).getFullDTO();
  }
}

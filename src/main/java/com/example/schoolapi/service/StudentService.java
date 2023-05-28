package com.example.schoolapi.service;

import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Student;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.StudentRepository;
import com.example.schoolapi.repository.TeacherRepository;
import com.example.schoolapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public Student getStudent(Long id) {
    Student student = studentRepository.findById(id).get();
    return student;
  }

  public Student addEducation(Long id, Long educationId) {
    Student student = studentRepository.findById(id).get();
    Education education = educationRepository.findById(educationId).get();
    for(Long teacherId:education.getTeacherId()) {
      Teacher teacher = teacherRepository.findById(teacherId).get();
      teacher.getStudentId().add(id);
      teacherRepository.save(teacher);
    }
    student.setEducationId(educationId);
    student.getTeacherIds().addAll(education.getTeacherId());
    return studentRepository.save(student);
  }
}

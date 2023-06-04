package com.example.schoolapi.service.auth;

import com.example.schoolapi.dto.StudentDto;
import com.example.schoolapi.dto.StudentSignUpDto;
import com.example.schoolapi.dto.TeacherDto;
import com.example.schoolapi.dto.TeacherSignUpDto;
import com.example.schoolapi.model.Student;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.model.User;
import com.example.schoolapi.model.role.ERole;
import com.example.schoolapi.model.role.Role;
import com.example.schoolapi.repository.RoleRepository;
import com.example.schoolapi.repository.StudentRepository;
import com.example.schoolapi.repository.TeacherRepository;
import com.example.schoolapi.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

  private StudentRepository studentRepository;
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final TeacherRepository teacherRepository;

  @Autowired
  public RegistrationService(StudentRepository studentRepository,
      RoleRepository roleRepository,
      UserRepository userRepository,
      TeacherRepository teacherRepository) {
    this.studentRepository = studentRepository;
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.teacherRepository = teacherRepository;
  }

  public StudentDto registerStudent(StudentSignUpDto studentSignUpDto) {
    Student student = new Student(studentSignUpDto);
    Role role = roleRepository.findByName(ERole.STUDENT).get();
    User user = new User(studentSignUpDto.getEmail(), role.getName().toString());
    userRepository.save(user);

    student.setUserId(user.getId());

    return studentRepository.save(student).getFullDTO();
  }

  public TeacherDto registerTeacher(TeacherSignUpDto teacherSignUpDto) {
    Teacher teacher = new Teacher(teacherSignUpDto);
    Role role = roleRepository.findByName(ERole.TEACHER).get();
    User user = new User(teacherSignUpDto.getEmail(), role.getName().toString());
    userRepository.save(user);

    teacher.setUserId(user.getId());

    return teacherRepository.save(teacher).getFullDto();
  }
}

package com.example.schoolapi.service;

import com.example.schoolapi.dto.LessonBookedDto;
import com.example.schoolapi.dto.LessonDto;
import com.example.schoolapi.model.lesson.ELesson;
import com.example.schoolapi.model.lesson.Lesson;
import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.Teacher;
import com.example.schoolapi.repository.LessonRepository;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.StudentRepository;
import com.example.schoolapi.repository.TeacherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EducationService {
  
  private final EducationRepository educationRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;
  private final LessonRepository lessonRepository;

  @Autowired
  public EducationService(EducationRepository educationRepository,
      StudentRepository studentRepository,
      TeacherRepository teacherRepository,
      LessonRepository classRepository) {
    this.educationRepository = educationRepository;
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
    this.lessonRepository = classRepository;
  }

  public void terminateStudent(Long educationId, Long studentId) {
    if(educationRepository.findById(educationId).isPresent()) {
      Education education = educationRepository.findById(educationId).get();
      if(education.getStudentId().contains(studentId)) {
        System.out.println(1);
        education.getStudentId().remove(studentId);
        System.out.println(2);
        educationRepository.save(education);
        return;
      }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student was not found in this education");
    }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education could not be found");
  }

  public List<Education> getAllEducations() {

    return educationRepository.findAll();
  }



  public LessonBookedDto bookLesson(Long teacherId, LessonDto classDto) {
    if(teacherRepository.findById(teacherId).isPresent()) {
      Teacher teacher =  teacherRepository.findById(teacherId).get();
      if(teacher.getEducationId().contains(classDto.getEducationId())) {
        Lesson lesson = new Lesson(classDto);
        return lessonRepository.save(lesson).getFullDto();
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher and education is not connected");
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher could not be found");
  }


  public LessonBookedDto startLesson(Long lessonId) {
    if(lessonRepository.findById(lessonId).isPresent()) {
      Lesson lesson = lessonRepository.findById(lessonId).get();
      if(lesson.getState() == ELesson.BOOKED) {
        lesson.setState(ELesson.STARTED);
        return lessonRepository.save(lesson).getFullDto();
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson is started or finished");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson could not be found");
  }

  public LessonBookedDto finishLesson(Long lessonId) {
    if(lessonRepository.findById(lessonId).isPresent()) {
      Lesson lesson = lessonRepository.findById(lessonId).get();
      if(lesson.getState() == ELesson.STARTED) {
        lesson.setState(ELesson.FINISHED);
        return lessonRepository.save(lesson).getFullDto();
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson is not started");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson could not be found");
  }
}

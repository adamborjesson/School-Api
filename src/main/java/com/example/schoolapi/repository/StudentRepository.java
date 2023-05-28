package com.example.schoolapi.repository;

import com.example.schoolapi.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


  Optional<Student> findByName(String name);
}

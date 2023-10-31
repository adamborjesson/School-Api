package com.example.schoolapi.repository;

import com.example.schoolapi.model.Education;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

  Optional<Education> findByIdAndStudentId(Long educationId, Long studentId);

}

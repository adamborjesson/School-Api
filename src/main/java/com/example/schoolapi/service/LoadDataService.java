package com.example.schoolapi.service;


import com.example.schoolapi.model.Education;
import com.example.schoolapi.model.role.ERole;
import com.example.schoolapi.model.role.Role;
import com.example.schoolapi.repository.EducationRepository;
import com.example.schoolapi.repository.RoleRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDataService implements CommandLineRunner {

private final EducationRepository educationRepository;
private final RoleRepository roleRepository;

@Autowired
public LoadDataService(EducationRepository educationRepository, RoleRepository roleRepository) {
  this.educationRepository = educationRepository;
  this.roleRepository = roleRepository;
}

  @Override
  public void run(String... args) throws IOException {

  if(educationRepository.findAll().isEmpty()) {
    List<Education> educations = new ArrayList<>();

    educations.add(new Education("Java"));
    educations.add(new Education("C#"));

    educationRepository.saveAll(educations);
  }
  if(roleRepository.findAll().isEmpty()) {
    ERole[] roleNames = ERole.values();
    for (ERole r:roleNames) {
      Role role = new Role();
      role.setName(r);
      roleRepository.save(role);
    }
  }
  }

}

package com.patzzzcode.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.bo.Project;

public interface PersonRepository extends JpaRepository<Person, Long> {
  List<Person> findByIsAvailable(Boolean isAvailable);

  List<Person> findByAssignedProject(Project assignedProject);
}

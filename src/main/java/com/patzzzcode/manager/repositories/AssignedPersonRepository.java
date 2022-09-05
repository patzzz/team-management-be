package com.patzzzcode.manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patzzzcode.manager.bo.AssignedPerson;
import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.bo.Project;

public interface AssignedPersonRepository extends JpaRepository<AssignedPerson, Long> {
  Optional<AssignedPerson> findByProjectAndPerson(Project project, Person person);

  List<AssignedPerson> findByProject(Project project);

  List<AssignedPerson> findByPerson(Person person);
}

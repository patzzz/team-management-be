package com.patzzzcode.manager.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patzzzcode.manager.bo.AssignedPerson;
import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.bo.Project;
import com.patzzzcode.manager.repositories.AssignedPersonRepository;

@Service
public class AssignedPersonsService {

  @Autowired
  private AssignedPersonRepository assignedPersonRepository;
  @Autowired
  private PersonService personService;

  public AssignedPerson assignOrUnassignPerson(Project project, Person person) {
    AssignedPerson existignAssign = assignedPersonRepository.findByProjectAndPerson(project, person).orElse(null);
    if (Objects.nonNull(existignAssign)) {
      assignedPersonRepository.delete(existignAssign);
      personService.setPersonAvailableStatus(person, project, true);
      return null;
    } else {
      AssignedPerson newAssign = new AssignedPerson();
      newAssign.setPerson(person);
      newAssign.setProject(project);
      newAssign = assignedPersonRepository.save(newAssign);
      personService.setPersonAvailableStatus(person, project, false);
      return newAssign;
    }
  }
}

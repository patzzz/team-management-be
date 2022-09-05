package com.patzzzcode.manager.services;

import java.util.ArrayList;
import java.util.List;
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

  public List<Person> getAssignedPersons(Project project) {
    List<AssignedPerson> assignedPersons = assignedPersonRepository.findByProject(project);
    List<Person> persons = new ArrayList<Person>();

    for (AssignedPerson assignedPerson : assignedPersons) {
      persons.add(assignedPerson.getPerson());
    }

    return persons;
  }

  public List<Project> getAssignedProjects(Person person) {
    List<AssignedPerson> assignedPersons = assignedPersonRepository.findByPerson(person);
    List<Project> projects = new ArrayList<Project>();

    for (AssignedPerson assignedPerson : assignedPersons) {
      projects.add(assignedPerson.getProject());
    }

    return projects;
  }
}

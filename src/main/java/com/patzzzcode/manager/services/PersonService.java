package com.patzzzcode.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.bo.Project;
import com.patzzzcode.manager.repositories.PersonRepository;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public Person createPerson(Person person) {
    Person newPerson = new Person();
    newPerson.setFirstName(person.getFirstName());
    newPerson.setLastName(person.getLastName());
    newPerson.setPosition(person.getPosition());
    newPerson.setIsAvailable(true);
    newPerson.setAssignedProject(null);
    newPerson = personRepository.save(newPerson);
    return newPerson;
  }

  public void setPersonAvailableStatus(Person person, Project project, Boolean status) {
    if (status) {
      person.setIsAvailable(true);
      person.setAssignedProject(null);
    } else {
      person.setIsAvailable(false);
      person.setAssignedProject(project);
    }
    personRepository.save(person);
  }

  public List<Person> getByAvailabilityStatus(String status) {
    if (status.equals("AVAILABLE")) {
      return personRepository.findByIsAvailable(true);
    } else {
      return personRepository.findByIsAvailable(false);
    }
  }

}

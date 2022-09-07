package com.patzzzcode.manager.restcontrollers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patzzzcode.manager.bo.AssignedPerson;
import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.repositories.AssignedPersonRepository;
import com.patzzzcode.manager.repositories.PersonRepository;
import com.patzzzcode.manager.services.AssignedPersonsService;
import com.patzzzcode.manager.services.PersonService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PersonController {

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private AssignedPersonRepository assignedPersonRepository;

  @Autowired
  private PersonService personService;
  @Autowired
  private AssignedPersonsService assignedPersonsService;

  @RequestMapping(value = "/person/create", method = RequestMethod.POST)
  public ResponseEntity<Object> createPerson(@RequestBody Person person) {
    try {
      Person newPerson = personService.createPerson(person);
      return new ResponseEntity<Object>(newPerson, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/person", method = RequestMethod.GET)
  public ResponseEntity<Object> getPerson(@RequestParam Long personId) {
    try {
      Person existingPerson = personRepository.findById(personId).orElse(null);
      if (Objects.nonNull(existingPerson)) {
        return new ResponseEntity<Object>(existingPerson, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/person/all", method = RequestMethod.GET)
  public ResponseEntity<Object> getAllPersons() {
    try {
      List<Person> existingPerson = personRepository.findAll();
      return new ResponseEntity<Object>(existingPerson, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/person/update", method = RequestMethod.PUT)
  public ResponseEntity<Object> updatePerson(@RequestParam Long personId, @RequestBody Person person) {
    try {
      Person existingPerson = personRepository.findById(personId).orElse(null);
      if (Objects.nonNull(existingPerson)) {
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setPosition(person.getPosition());
        personRepository.save(existingPerson);
        return new ResponseEntity<Object>(existingPerson, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/person/delete")
  public ResponseEntity<Object> deletePerson(@RequestParam Long personId) {
    try {
      Person existingPerson = personRepository.findById(personId).orElse(null);
      if (Objects.nonNull(existingPerson)) {
        List<AssignedPerson> assignedProjects = assignedPersonRepository.findByPerson(existingPerson);
        for (AssignedPerson ap : assignedProjects) {
          assignedPersonRepository.delete(ap);
        }
        personRepository.delete(existingPerson);
        return new ResponseEntity<Object>(HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/person/getAssignedProjects", method = RequestMethod.GET)
  public ResponseEntity<Object> getAssignedProjects(@RequestParam Long personId) {
    try {
      Person existingPerson = personRepository.findById(personId).orElse(null);
      if (Objects.nonNull(existingPerson)) {
        return new ResponseEntity<Object>(assignedPersonsService.getAssignedProjects(existingPerson), HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/person/getPersonsByAvailabilityStatus", method = RequestMethod.GET)
  public ResponseEntity<Object> getPersonsByAvailabilityStatus(@RequestParam String status) {
    try {
      return new ResponseEntity<Object>(personService.getByAvailabilityStatus(status), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}

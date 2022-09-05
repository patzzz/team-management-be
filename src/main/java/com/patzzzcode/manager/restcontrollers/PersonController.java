package com.patzzzcode.manager.restcontrollers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.repositories.PersonRepository;
import com.patzzzcode.manager.services.PersonService;

@RestController
public class PersonController {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonService personService;

  @RequestMapping(value = "/api/person", method = RequestMethod.POST)
  public ResponseEntity<Object> createPerson(@RequestBody Person person) {
    try {
      Person newPerson = personService.createPerson(person);
      return new ResponseEntity<Object>(newPerson, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/person", method = RequestMethod.GET)
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

  @RequestMapping(value = "/api/person/all", method = RequestMethod.GET)
  public ResponseEntity<Object> getAllPersons() {
    try {
      List<Person> existingPerson = personRepository.findAll();
      return new ResponseEntity<Object>(existingPerson, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/person", method = RequestMethod.PUT)
  public ResponseEntity<Object> updatePerson(@RequestParam Long personId, @RequestBody Person person) {
    try {
      Person existingPerson = personRepository.findById(personId).orElse(null);
      if (Objects.nonNull(existingPerson)) {
        Person updatedPerson = personService.updatePerson(existingPerson, person);
        return new ResponseEntity<Object>(updatedPerson, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/person", method = RequestMethod.DELETE)
  public ResponseEntity<Object> deletePerson(@RequestParam Long personId) {
    try {
      Person existingPerson = personRepository.findById(personId).orElse(null);
      if (Objects.nonNull(existingPerson)) {
        personRepository.delete(existingPerson);
        return new ResponseEntity<Object>(HttpStatus.GONE);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}

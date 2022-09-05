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
import com.patzzzcode.manager.bo.Project;
import com.patzzzcode.manager.bo.Statistics;
import com.patzzzcode.manager.repositories.PersonRepository;
import com.patzzzcode.manager.repositories.ProjectRepository;
import com.patzzzcode.manager.services.AssignedPersonsService;
import com.patzzzcode.manager.services.ProjectService;
import com.patzzzcode.manager.services.StatisticsService;

@RestController
public class ProjectController {

  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ProjectService projectService;
  @Autowired
  private AssignedPersonsService assignedPersonsService;
  @Autowired
  private StatisticsService statisticsService;

  @RequestMapping(value = "/api/project", method = RequestMethod.POST)
  public ResponseEntity<Object> createProject(@RequestBody Project project) {
    try {
      Project newProject = projectService.createProject(project);
      return new ResponseEntity<Object>(newProject, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/project", method = RequestMethod.GET)
  public ResponseEntity<Object> getProject(@RequestParam Long projectId) {
    try {
      Project existingproject = projectRepository.findById(projectId).orElse(null);
      if (Objects.nonNull(existingproject)) {
        return new ResponseEntity<Object>(existingproject, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/project/all", method = RequestMethod.GET)
  public ResponseEntity<Object> getAllProjects() {
    try {
      List<Project> existingProject = projectRepository.findAll();
      return new ResponseEntity<Object>(existingProject, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/project", method = RequestMethod.PUT)
  public ResponseEntity<Object> updateProject(@RequestParam Long projectId, @RequestBody Project project) {
    try {
      Project existingProject = projectRepository.findById(projectId).orElse(null);
      if (Objects.nonNull(existingProject)) {
        Project updatedProject = projectService.updateProject(existingProject, project);
        return new ResponseEntity<Object>(updatedProject, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/project", method = RequestMethod.DELETE)
  public ResponseEntity<Object> deleteProject(@RequestParam Long projectId) {
    try {
      Project existingProject = projectRepository.findById(projectId).orElse(null);
      if (Objects.nonNull(existingProject)) {
        projectRepository.delete(existingProject);
        return new ResponseEntity<Object>(HttpStatus.GONE);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/project/assignOrUnassignPerson", method = RequestMethod.POST)
  public ResponseEntity<Object> assignOrUnassignPerson(@RequestParam Long projectId, @RequestParam Long personId) {
    try {
      Project existingProject = projectRepository.findById(projectId).orElse(null);
      Person existingPerson = personRepository.findById(personId).orElse(null);
      if (Objects.nonNull(existingProject) && Objects.nonNull(existingPerson)) {
        assignedPersonsService.assignOrUnassignPerson(existingProject, existingPerson);
        return new ResponseEntity<Object>(HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/api/statistics", method = RequestMethod.GET)
  public ResponseEntity<Object> getStatistics() {
    try {
      Statistics statistics = statisticsService.getStatistics();
      return new ResponseEntity<Object>(statistics, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}

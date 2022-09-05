package com.patzzzcode.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.bo.Project;
import com.patzzzcode.manager.repositories.ProjectRepository;

@Service
public class ProjectService {
  @Autowired
  private ProjectRepository projectRepository;

  public Project createProject(Project project) {
    Project newProject = new Project();
    newProject.setTitle(project.getTitle());
    newProject.setDescription(project.getDescription());
    newProject.setStatus("PENDING");
    newProject.setStartDate(project.getStartDate());
    newProject.setDeadline(project.getDeadline());
    newProject = projectRepository.save(newProject);
    return newProject;
  }

  public Project updateProject(Project existingProject, Project project) {
    existingProject.setTitle(project.getTitle());
    existingProject.setDescription(project.getDescription());
    existingProject.setStatus(project.getStatus());
    existingProject.setStartDate(project.getStartDate());
    existingProject.setDeadline(project.getDeadline());
    existingProject.setEndDate(project.getEndDate());
    projectRepository.save(existingProject);
    return existingProject;
  }

}

package com.patzzzcode.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patzzzcode.manager.bo.Person;
import com.patzzzcode.manager.bo.Project;
import com.patzzzcode.manager.bo.Statistics;
import com.patzzzcode.manager.repositories.PersonRepository;
import com.patzzzcode.manager.repositories.ProjectRepository;

@Service
public class StatisticsService {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ProjectRepository projectRepository;

  public Statistics getStatistics() {
    Statistics statistics = new Statistics();
    statistics.setAvailablePersons(getAvailablePersons());
    statistics.setUpcomingDeadlines(getUpcomingDeadlines());
    statistics.setUpcomingStartDates(getUpcomingStartDates());
    statistics.setProjectsInProgress(getUpcomingDeadlines().size());
    statistics.setProjectsPending(getUpcomingStartDates().size());
    statistics.setProjectsDone(projectRepository.findByStatus("DONE").size());
    return statistics;
  }

  public List<Project> getUpcomingDeadlines() {
    List<Project> upcomingDeadlines = projectRepository.findByStatus("IN_PROGRESS");
    return upcomingDeadlines;
  }

  public List<Project> getUpcomingStartDates() {
    List<Project> upcomingStartDates = projectRepository.findByStatus("PENDING");
    return upcomingStartDates;
  }

  public List<Person> getAvailablePersons() {
    List<Person> availablePersons = personRepository.findByIsAvailable(true);
    return availablePersons;
  }
}

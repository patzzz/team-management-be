package com.patzzzcode.manager.bo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Statistics {
  private List<Project> upcomingDeadlines;
  private List<Project> upcomingStartDates;
  private List<Person> availablePersons;
  private int projectsInProgress;
  private int projectsPending;
  private int projectsDone;
}

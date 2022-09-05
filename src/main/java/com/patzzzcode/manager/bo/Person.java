package com.patzzzcode.manager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERSONS")
@Getter
@Setter
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String position;
  private String firstName;
  private String lastName;
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project assignedProject;
  private Boolean isAvailable;
}

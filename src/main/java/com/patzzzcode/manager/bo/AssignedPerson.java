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
@Table(name = "ASSIGNED_PERSONS")
@Getter
@Setter
public class AssignedPerson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;
}

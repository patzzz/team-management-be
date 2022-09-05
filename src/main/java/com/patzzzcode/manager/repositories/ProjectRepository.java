package com.patzzzcode.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.patzzzcode.manager.bo.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> findByStatus(String status);

  // @Query("SELECT COUNT(u) FROM Projects u WHERE project.status=?1")
  // Integer countByStatus(String status);

}

package com.patzzzcode.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patzzzcode.manager.bo.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

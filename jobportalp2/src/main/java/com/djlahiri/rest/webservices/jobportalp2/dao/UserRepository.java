package com.djlahiri.rest.webservices.jobportalp2.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.djlahiri.rest.webservices.jobportalp2.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	

}

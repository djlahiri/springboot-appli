package com.djlahiri.rest.webservices.jobportalp2.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.djlahiri.rest.webservices.jobportalp2.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

	
	Optional<Role>findByName(String username);
	
}
			






package com.djlahiri.rest.webservices.jobportalp2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.djlahiri.rest.webservices.jobportalp2.entities.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Long> {

}

package com.djlahiri.rest.webservices.jobportalp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djlahiri.rest.webservices.jobportalp2.Dto.JobApplicationDto;
import com.djlahiri.rest.webservices.jobportalp2.dao.JobApplicationRepository;

import com.djlahiri.rest.webservices.jobportalp2.dao.UserRepository;
import com.djlahiri.rest.webservices.jobportalp2.entities.JobApplication;
import com.djlahiri.rest.webservices.jobportalp2.entities.Jobs;

@Controller
@RequestMapping("/api/jobs/")
public class JobApplicationController {
	
	private JobApplicationRepository jobapplicationrepo;
	private UserRepository userrepo;

	
	
	
	JobApplication apply=new JobApplication();
// @GetMapping("apply/{id}")
// public String applyJob(Model model) {
//	 
//	 model.addAttribute("application", new JobApplicationDto());
//	 
//	 Jobs job=
//	 apply.setJobs(null)
//	 
//	 return "JobApplication";
//	 
// }
//	
//	

}

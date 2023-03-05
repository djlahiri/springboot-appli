package com.djlahiri.rest.webservices.jobportalp2.controller;
//
//import java.util.ArrayList;
//import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.djlahiri.rest.webservices.jobportalp2.dao.JobsRepository;
import com.djlahiri.rest.webservices.jobportalp2.entities.Jobs;

@Controller
public class JobsController {

	
	private JobsRepository jobsrepo;
	public JobsController(JobsRepository jobsrepo) {
		super();
		this.jobsrepo = jobsrepo;
	}


	
	
	
@GetMapping("/jobshome")
public String findAll(Model m){
	m.addAttribute("res", jobsrepo.findAll());
	return "jobshome";
	}

@GetMapping("/addNewJob")
public String addNewJob(Model model) {
	Jobs job=new Jobs();
	model.addAttribute("job", job);
	return "new_Job";
}

@PostMapping("/saveJob")
 public String saveJobs(@ModelAttribute("job") Jobs job) {
	jobsrepo.save(job);
	return "redirect:/jobshome";
}


@GetMapping("/showUpdateForm/{jid}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public String updateJob(@PathVariable(value="jid") long jid,Model model) {
	
	Jobs job=jobsrepo.findById(jid).get();
	model.addAttribute("job", job);
	return "update_Job";
}

//
//@PutMapping("updateJob")
//public String updatetheJob(@ModelAttribute("job") Jobs job) {
//	jobsrepo.save(job);
//	return "redirect:/jobshome";
//}
//@DeleteMapping("/jobsdelete/{id}")
//public String deleteJobs(@PathVariable long id) {
//	jobsrepo.deleteById(id);
//	return ("job with id:"+id+"deleted");
//}


}

package com.djlahiri.rest.webservices.jobportalp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.djlahiri.rest.webservices.jobportalp2.Dto.UserRegistrationDto;
import com.djlahiri.rest.webservices.jobportalp2.dao.UserRepository;
import com.djlahiri.rest.webservices.jobportalp2.entities.User;

public class RegistrationController {
	
	
	private UserRepository userrepo;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public RegistrationController(UserRepository userrepo, PasswordEncoder passwordEncoder) {
		super();
		this.userrepo = userrepo;
		this.passwordEncoder = passwordEncoder;
	}



	@PostMapping("register")
	public String saveUser(@RequestBody UserRegistrationDto userRegistrationDto) {
		User user=new User();
				user.setEmail(userRegistrationDto.getEmail());
				user.setName(userRegistrationDto.getName());
				user.setUsername(userRegistrationDto.getUsername());
				user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
//				Role roles = rolerepo.findByName("USER").get();
//		        user.setRoles(Collections.singletonList(roles));
				
					userrepo.save(user);
		return "registration";}

}

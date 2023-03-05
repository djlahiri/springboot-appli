package com.djlahiri.rest.webservices.jobportalp2.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djlahiri.rest.webservices.jobportalp2.Dto.LoginDto;
import com.djlahiri.rest.webservices.jobportalp2.Dto.UserRegistrationDto;
import com.djlahiri.rest.webservices.jobportalp2.dao.RoleRepository;
import com.djlahiri.rest.webservices.jobportalp2.dao.UserRepository;
import com.djlahiri.rest.webservices.jobportalp2.entities.Role;
import com.djlahiri.rest.webservices.jobportalp2.entities.User;
import com.djlahiri.rest.webservices.jobportalp2.security.CustomUserDetails;
import com.djlahiri.rest.webservices.jobportalp2.security.JwtGenerator;

@Controller
@RequestMapping("/api/auth/")
public class UserRegistrationController {
	
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private UserRepository userrepo;
	private RoleRepository rolerepo;
	private JwtGenerator jwtGenerator;
	private CustomUserDetails userService;

	
	@Autowired
	public UserRegistrationController(AuthenticationManager authenticationManager, PasswordEncoder passwordencoder,
			UserRepository userrepo, RoleRepository rolerepo,JwtGenerator jwtGenerator,CustomUserDetails userService) {
		super();
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.userrepo = userrepo;
		this.rolerepo = rolerepo;
		this.jwtGenerator=jwtGenerator;
		this.userService=userService;
	}


@GetMapping("signup")
public String register(Model model){
	model.addAttribute("userRegistrationDto", new UserRegistrationDto());
	return "registrationpage";
	}


@PostMapping("register")
public String saveUser(@RequestBody UserRegistrationDto userRegistrationDto) {
	System.out.println("Registration  Envoked");
	User user=new User();
			user.setEmail(userRegistrationDto.getEmail());
			user.setName(userRegistrationDto.getName());
			user.setUsername(userRegistrationDto.getUsername());
//			user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
			user.setPassword(userRegistrationDto.getPassword());
			
			List<Role>roles=new ArrayList<>();
		Role role=new Role(1,"USER");
	        roles.add(role);
	        user.setRoles(roles);
			
	        User savedUser=userrepo.save(user);
	        System.out.println(savedUser.getName());
	return "registration";}

@GetMapping("login")
public String login(Model model) {
	model.addAttribute("loginDto", new LoginDto());
	return "login";
	}

@PostMapping("login")
public String authenticate(@ModelAttribute("loginDto") LoginDto loginDto, Model model) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        model.addAttribute("accessToken", token);
        return "registration";
    } catch (AuthenticationException e) {
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}

}

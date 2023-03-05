package com.djlahiri.rest.webservices.jobportalp2.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.djlahiri.rest.webservices.jobportalp2.dao.UserRepository;
import com.djlahiri.rest.webservices.jobportalp2.entities.Role;

@Service
public class CustomUserDetails implements UserDetailsService {
	
	private UserRepository userrepo;
	
@Autowired
	public CustomUserDetails(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	com.djlahiri.rest.webservices.jobportalp2.entities.User user=userrepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not Found"));
	
	return new User(user.getUsername(),user.getPassword(),mapRolestoAuthorities(user.getRoles()));}
	
	private Collection<GrantedAuthority>mapRolestoAuthorities(List<Role>roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
	}
	
	
	
	

}

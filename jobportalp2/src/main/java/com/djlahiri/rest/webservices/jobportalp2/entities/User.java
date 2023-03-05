package com.djlahiri.rest.webservices.jobportalp2.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	@Column(unique=true)
	private String username;
	private String password;
	
	private String resumeurl;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="user_roles",
	joinColumns=@JoinColumn(name="user_id",referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id"))
	private List<Role>roles=new ArrayList<>();
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User(int id, String name, String email, String username, String password, String resumeurl,
			List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.resumeurl = resumeurl;
		this.roles = roles;
	}



	public List<Role> getRoles() {
		return roles;
	}



	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		public String getResumeurl() {
		return resumeurl;
	}
	public void setResumeurl(String resumeurl) {
		this.resumeurl = resumeurl;
	}


//	public void setName(String name2) {
//		// TODO Auto-generated method stub
//		
//	}
//	

}

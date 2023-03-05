package com.djlahiri.rest.webservices.jobportalp2.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Jobs")
public class Jobs {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long  jid;
	private String title;
	@Column(length=500)
	private String description;
	private int yoe;
	private int compensation;
	
	 @OneToMany(mappedBy = "jobs", cascade = CascadeType.ALL)
	 private List<JobApplication>jobapplications=new ArrayList<>();
	 
	 
	 
	 
	public List<JobApplication> getJobapplications() {
		return jobapplications;
	}
	public void setJobapplications(List<JobApplication> jobapplications) {
		this.jobapplications = jobapplications;
	}
	public long getJid() {
		return jid;
	}
	public void setJid(long jid) {
		this.jid = jid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getYoe() {
		return yoe;
	}
	public void setYoe(int yoe) {
		this.yoe = yoe;
	}
	public int getCompensation() {
		return compensation;
	}
	public void setCompensation(int compensation) {
		this.compensation = compensation;
	}
	
	
	
	
	
	

}

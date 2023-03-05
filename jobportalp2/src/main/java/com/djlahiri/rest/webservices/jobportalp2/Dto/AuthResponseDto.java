package com.djlahiri.rest.webservices.jobportalp2.Dto;

public class AuthResponseDto {
	
	private String accessToken;
	
	public String tokenType="Bearer ";

	public AuthResponseDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	

}

package com.djlahiri.rest.webservices.jobportalp2.security;



import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtGenerator {
	
	
	
	
	public String generateToken(Authentication authentication) {
		byte [] key=Decoders.BASE64.decode(SecurityConstants.JWT_SECRET);
		 String username = authentication.getName();
	        Date currentDate = new Date();
	        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
	        
	        
	        

	        String token = Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(currentDate)
	                .setExpiration(expireDate)
	                .signWith(Keys.hmacShaKeyFor(key), SignatureAlgorithm.HS512)
	                .compact();
	        return token; 
	    }

	    public String getUsernameFromJWT(String token) {
//	    	  Claims claims = Jwts.parser()
//	                  .setSigningKey(key)
//	                  .parseClaimsJws(token)
//	                  .getBody();
//	          return claims.getSubject();
	    	
	    	SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SecurityConstants.JWT_SECRET));
	    	Claims claims=Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
	    	return claims.getSubject();
	    }

	    public boolean validateToken(String token) {
	        try {
	        	SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SecurityConstants.JWT_SECRET));
	        	Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
	        	return true;
	        } catch (Exception ex) {
	            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
	        }
	    }
	
	

}

package com.djlahiri.rest.webservices.jobportalp2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity 
@EnableMethodSecurity
public class SecurityConfig {
	
	private CustomUserDetails userDetailService;

	private JwtAuthEntryPoint authEntryPoint;

	@Autowired
	public SecurityConfig(CustomUserDetails userDetailService, JwtAuthEntryPoint authEntryPoint) {
		super();
		this.userDetailService = userDetailService;
		this.authEntryPoint = authEntryPoint;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		 http.csrf().disable()
//		 .exceptionHandling()
//         .authenticationEntryPoint(authEntryPoint)
//         .and()
//         .sessionManagement()
//         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         .and()
//         .authorizeHttpRequests()
//         .requestMatchers(HttpMethod.POST, "/api/auth/register")
//         .permitAll().and()
         .authorizeHttpRequests().requestMatchers("/*/*.html").permitAll().and()
	.authorizeHttpRequests().requestMatchers("/api/auth/**","registration","/jobshome","register").permitAll().and()
	.exceptionHandling()
    .authenticationEntryPoint(authEntryPoint)
    .and()
    .sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
	.httpBasic();
		 http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		 
		 return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	
	@Bean
	 public JWTAuthFilter jwtAuthenticationFilter() {
		return new JWTAuthFilter();
	}

}

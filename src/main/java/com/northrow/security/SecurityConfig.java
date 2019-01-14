package com.northrow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * <h1>SecurityConfig</h1>
 * <p>This program provides a basic implementation for basic authentication. The username, password
 * and role details are being fetched from application.properties file.</p>
 * @author Prasad Ramalingam
 * @version 1.0
 *
 */
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FreezerBasicAuthenticationEntryPoint freezerBasicAuthenticationEntryPoint;

	@Value("${security.user.name}")
	private String username;
	
	@Value("${security.user.password}")
	private String password;
	
	@Value("${security.user.role}")
	private String role;

	/**
	 * This method uses the username , password and role for authentication.
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(username).password(password).roles(role);
	}

	/**
	 * This method is used to provide the list of urls that require authentication.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/h2/**","/console/**").permitAll() // added for h2 database console access
		    .and()
		    .authorizeRequests().antMatchers("/food","/food/**","/food/search","/food/search/**").authenticated()
		    .and()
			.httpBasic()
			.authenticationEntryPoint(freezerBasicAuthenticationEntryPoint);
		
		http.csrf().disable();
	    http.headers().frameOptions().disable();
	}
	
}
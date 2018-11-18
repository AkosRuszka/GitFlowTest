package com.blogengine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.httpBasic().and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/blogpost/**").permitAll()
		.antMatchers(HttpMethod.PUT,"/blogpost/update").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/blogpost/new","/blogpost/follow/**").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "/blogpost/delete/**").hasRole("USER")
		.antMatchers("/comment").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/blogger/list").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/blogger/profile").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/blogger/**").hasRole("USER")
		.antMatchers(HttpMethod.PUT, "/blogger").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/blogger").permitAll()
		.antMatchers(HttpMethod.DELETE, "/blogger/profile").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "/blogger/**").hasRole("ADMIN")
		.anyRequest().authenticated();		
//		.and()
//		.formLogin().permitAll()
//		.and()
//		.logout().permitAll();
		
	}
}

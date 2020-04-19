package com.amsidh.mvc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("WebSecurityConfiguration's configure(AuthenticationManagerBuilder auth) method");
		auth.inMemoryAuthentication().withUser("amsidhlokhande").password("{noop}Password@123")
				.roles(new String[] { "USER" }).and().withUser("amsidhlokh").password("{noop}password")
				.roles(new String[] { "ADMIN" });
	}

	/*
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception { System.out.
	 * println("WebSecurityConfiguration's configure(HttpSecurity httpSecurity) method"
	 * ); httpSecurity.authorizeRequests().anyRequest() //.permitAll()
	 * .authenticated() .and().httpBasic(); httpSecurity.csrf().disable();
	 * 
	 * }
	 */

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll();
	}
}

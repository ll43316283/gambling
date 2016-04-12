package com.math040.gambling.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication()
			.withUser("liang")
			.password("test")
			.authorities("ROLE_USER");
	}
	
	 @Override
     public void configure(WebSecurity web) throws Exception {
       web
         .ignoring()
            .antMatchers("/resources/**").antMatchers("/console/**");
     }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().permitAll() 
		.and().authorizeRequests().antMatchers("/console/**").permitAll()
			.and().csrf().disable()
			.authorizeRequests().antMatchers("/**").hasRole("USER").anyRequest().authenticated()
			;
		
	}
}

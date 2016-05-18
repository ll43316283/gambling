package com.math040.gambling.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.math040.gambling.service.UserService;
import com.math040.gambling.service.impl.GamblingUserDetailServiceImpl;
import com.math040.gambling.service.impl.SimpleLoginSucessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
	
	 @Override
     public void configure(WebSecurity web) throws Exception {
       web
         .ignoring()
            .antMatchers("/resources/**").antMatchers("/console/**").antMatchers("/js/**");
     }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/**").hasAnyRole("USER","ADMIN").anyRequest().authenticated()
			; 
		http.csrf().disable().formLogin().loginPage("/login")  
        .failureUrl("/login?error=error")  
        .loginProcessingUrl("/j_spring_security_check")  
        .successHandler(getSuccessHandler())
        .usernameParameter("username")  
        .passwordParameter("password").permitAll()
        .and().authorizeRequests().antMatchers("/**").hasAnyRole("USER","ADMIN");  

 
		http.logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login")  
        .invalidateHttpSession(true);  
		http.rememberMe();
		
	}
	
	@Bean
	public AuthenticationSuccessHandler getSuccessHandler(){
		return new SimpleLoginSucessHandler();
	}
		
	@Autowired
	UserService userService; 
	
	 @Override  
	 protected void configure(AuthenticationManagerBuilder auth)  
	            throws Exception {   
	        auth.userDetailsService(getUserDetailService()); 
	 }  
	 
	 @Bean
	 public UserDetailsService getUserDetailService(){
		 return new GamblingUserDetailServiceImpl(userService); 
	 }
	 
}

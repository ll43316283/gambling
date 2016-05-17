package com.math040.gambling.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 

import com.math040.gambling.dto.User;
import com.math040.gambling.service.UserService;
 
public class GamblingUserDetailServiceImpl implements UserDetailsService {
	
	 
	private UserService userService;
	public GamblingUserDetailServiceImpl(UserService userService){
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException { 
		User dbuser = userService.findByUserName(userName);
		if(dbuser == null){
			throw new UsernameNotFoundException("User not found");  
		} 
		return new org.springframework.security.core.userdetails.User(dbuser.getUserName(), 
				dbuser.getPassword(), true, true, true, true,  
                getAuthorities(dbuser.getRole()));
	}
	
	public Collection<GrantedAuthority> getAuthorities(String role) {   
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);  
   
        authList.add(new SimpleGrantedAuthority(role));    
        return authList;  
    }  

}

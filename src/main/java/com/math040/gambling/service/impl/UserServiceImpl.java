package com.math040.gambling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.math040.gambling.dto.User;
import com.math040.gambling.repository.UserRepository;
import com.math040.gambling.service.UserService; 

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userDao;
	 
	
	public User save(User user){
		return userDao.save(user);
	}
	
	public User findByUserName(String userName ){
		return userDao.findByUserName( userName );
	}

	@Override
	public User getCurrent() {  
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal(); 
		User user = this.findByUserName(userDetails.getUsername());
//		System.out.println("current user:"+userDetails.getUsername());
		return user ;
	}
	 
}

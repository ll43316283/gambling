package com.math040.gambling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.math040.gambling.dto.UserDto;
import com.math040.gambling.repository.UserRepository;
import com.math040.gambling.service.UserService; 

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userDao;
	 
	
	public UserDto save(UserDto user){
		return userDao.save(user);
	}
	
	public UserDto findByUserName(String userName ){
		return userDao.findByUserName( userName );
	}
	 
}

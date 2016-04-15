package com.math040.gambling.service;
 

import com.math040.gambling.dto.UserDto;
 

public interface UserService { 
	public UserDto save(UserDto user); 
	public UserDto findByUserName(String userName );
}

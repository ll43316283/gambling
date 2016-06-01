package com.math040.gambling.service;
 

import com.math040.gambling.vo.User;
 

public interface UserService { 
	public User save(User user); 
	public User findByUserName(String userName );
	public User getCurrent();
}

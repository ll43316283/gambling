package com.math040.gambling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.math040.gambling.repository.AvatarRepository;
import com.math040.gambling.repository.UserRepository;
import com.math040.gambling.service.UserService;
import com.math040.gambling.vo.Avatar;
import com.math040.gambling.vo.User; 

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userDao;
	 
	@Autowired
	AvatarRepository avatarDao;
	
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
		return user ;
	}

	@Override
	public void updateAvatar(byte[] image) {
		User user = getCurrent(); 
		if(user.getAvatar()==null){ 
			Avatar ava = avatarDao.save(new Avatar());
			ava.setImage(image);
			user.setAvatar(ava); 
		}
		user.getAvatar().setImage(image);
		avatarDao.save(user.getAvatar());
		userDao.save(user);
	}
	
	
	 
}

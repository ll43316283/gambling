package com.math040.gambling.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.math040.gambling.dto.DebtDto;
import com.math040.gambling.dto.UserDto;
import com.math040.gambling.service.DebtService;
import com.math040.gambling.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/put", method = RequestMethod.GET)
	@ResponseBody
	public UserDto create(){ 
		UserDto user = new UserDto();
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRole(UserDto.ROLE_ADMIN);
		return userService.save(user);
	}
	  

}

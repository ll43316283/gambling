package com.math040.gambling.controller;
 
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.math040.gambling.GamblingException;
import com.math040.gambling.service.UserService;
import com.math040.gambling.vo.Avatar;
import com.math040.gambling.vo.User;

@Controller
@RequestMapping("/user")
public class UserController  extends BaseController {
	
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/put", method = RequestMethod.GET)
	@ResponseBody
	public User create(){ 
		User user = new User();
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRole(User.ROLE_ADMIN);
		return userService.save(user);
	}
	  
	@RequestMapping(value="/{userName}", method = RequestMethod.GET) 
	public String get(@PathVariable String userName,ModelMap model) throws GamblingException{
		if(!userService.getCurrent().getUserName().equals(userName)){
			throw new GamblingException(GamblingException.USER_NOT_CURRENT_USER);
		}
		return "user_profile";
	}
	
	@RequestMapping(value="/pic/{userName}", method = RequestMethod.GET)
	public void getPic(@PathVariable String userName,HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/octet-stream;charset=UTF-8"); 
		Avatar avatar = userService.findByUserName(userName).getAvatar();
		try {
			BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			byte[] img;
			if(avatar==null || avatar.getImage()==null || avatar.getImage().length<=0){
				InputStream f = UserController.class.getResourceAsStream("/user_default_avatar.jpg"); 
				img = new byte[f.available()];
			    f.read(img); 
			}else{
				img = avatar.getImage();
			} 
			outputStream.write(img);
			outputStream.flush();  
	        outputStream.close(); 
		} catch (Exception e) { 
		}  
		 
	}
	
	
	@RequestMapping(value="/{userName}", method = RequestMethod.POST) 
	public String update(@PathVariable String userName,@RequestParam(value = "file", required = false) MultipartFile file,ModelMap model) throws GamblingException{
		Assert.notNull(file);
		if(!userService.getCurrent().getUserName().equals(userName)){
			throw new GamblingException(GamblingException.USER_NOT_CURRENT_USER); 
		}
		try {
			file.getInputStream();
			userService.updateAvatar(file.getBytes());
		} catch (IOException e) {
			throw new GamblingException(GamblingException.USER_AVATAR_UPLOAD_FAILED);
		}
		return "redirect:/debt/list";
	}
}

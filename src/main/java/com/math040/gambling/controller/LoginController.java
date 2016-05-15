package com.math040.gambling.controller;
  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView; 

@Controller
@RequestMapping("/login")
public class LoginController  extends BaseController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="error",required=false) String error){
		return new ModelAndView("login","error",error);
	}
	  

}

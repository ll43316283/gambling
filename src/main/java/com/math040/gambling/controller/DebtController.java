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
import com.math040.gambling.service.DebtService;
import com.math040.gambling.service.UserService;

@Controller
@RequestMapping("/debt")
public class DebtController {
	
	private static Logger logger = LoggerFactory.getLogger(DebtController.class);
	
	@Autowired
	private DebtService debtService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public List<DebtDto> list(){
		logger.error("DebtController.list");
		return debtService.findAll();
	}
	
	/**
	 *   
	 * @return
	 */  
	@RequestMapping(value="/put", method = RequestMethod.GET)
	@ResponseBody
	public Long put(){
		DebtDto debt = new DebtDto();
		debt.setTitle("first debt");
		debt.setUser(userService.findByUserName("admin"));
		return debtService.save(debt);
	}

}

package com.math040.gambling.controller;
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;  
import com.math040.gambling.GamblingException; 
import com.math040.gambling.dto.Transaction; 
import com.math040.gambling.service.TransactionService;
import com.math040.gambling.service.UserService; 

@Controller
@RequestMapping("/transaction")
public class TransactionController extends BaseController{
	
	public static Logger logger = LoggerFactory.getLogger(TransactionController.class);
	 
	
	@Autowired
	private TransactionService transService;
	 
	@Autowired
	private UserService userService;
	
	@RequestMapping( method = RequestMethod.POST) 
	public String createTrans(Transaction transaction) throws GamblingException{ 
		Assert.notNull(transaction,"System Error");
		transaction.transferPredictAndAmount();
		transaction.setGambler(userService.getCurrent());
		transService.create(transaction);
		return "redirect:/debt/list";
	}
	 
}

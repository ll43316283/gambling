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

@Controller
@RequestMapping("/transaction")
public class TransactionController extends BaseController{
	
	public static Logger logger = LoggerFactory.getLogger(TransactionController.class);
	 
	
	@Autowired
	private TransactionService transService;
	 
	
	@RequestMapping( method = RequestMethod.POST) 
	public String createTrans(Transaction transaction) throws GamblingException{ 
		Assert.isTrue(transaction.validatePredict(),
				"system error:not correct ammount and side param");
		transaction.setPredict(transaction.getSideAmmount().substring(0, 1));
		transaction.setAmount(Integer.parseInt(transaction.getSideAmmount().substring(1))); 
		transService.create(transaction);
		return "redirect:/debt/list";
	}
	 
}

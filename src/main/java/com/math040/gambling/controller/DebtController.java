package com.math040.gambling.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Debt;
import com.math040.gambling.service.DebtService;
import com.math040.gambling.service.UserService;

@Controller
@RequestMapping("/debt")
public class DebtController {
	
	public static Logger logger = LoggerFactory.getLogger(DebtController.class);
	
	@Autowired
	private DebtService debtService;
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping(value="/list", method = RequestMethod.GET) 
	public String list(ModelMap model) throws GamblingException{ 
		List<Debt> debts = debtService.findCurrentSeasonInProcss();
		model.addAttribute("debts", debts); 
		return "debt_in_process";
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET) 
	public String goToNew() throws GamblingException{  
		return "add_debt";
	}
	
	/**
	 *   
	 * @return
	 */  
	@RequestMapping(method=RequestMethod.POST) 
	@ResponseBody
	public Debt create(Debt req){ 
		req.convertStrToDate();
		return req;
	}
}

package com.math040.gambling.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Debt;
import com.math040.gambling.dto.Transaction;
import com.math040.gambling.service.DebtService;
import com.math040.gambling.service.TransactionService;
import com.math040.gambling.service.UserService;

@Controller
@RequestMapping("/debt")
public class DebtController extends BaseController{
	
	public static Logger logger = LoggerFactory.getLogger(DebtController.class);
	
	@Autowired
	private DebtService debtService;
	
	@Autowired
	private TransactionService transService;
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping(value="/list", method = RequestMethod.GET) 
	public ModelAndView list() throws GamblingException{ 
		return new ModelAndView("debt_in_process","debts",debtService.findCurrentSeasonInProcss());
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET) 
	public String goToNew() throws GamblingException{  
		return "add_debt";
	}
	 
	@RequestMapping(method=RequestMethod.POST)  
	public String create(Debt req) throws GamblingException{ 
		req.convertStrToDate();
		req.setDealer(userService.getCurrent()); 
	    debtService.create(req);
	    return "redirect:/debt/list";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public String get(@PathVariable Long id,ModelMap model) throws GamblingException{
		Debt debt = debtService.findById(id);
		List<Transaction> transList = transService.findByDebt(debt);
		Map<String, List<Integer>> predicts = transService.getAvailablePredictAmounts(debt);
		model.put("debt", debt);
		model.put("transList", transList); 
		model.put("predicts", predicts);
		return "wager_a_debt";
	}
}

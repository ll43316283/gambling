package com.math040.gambling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.math040.gambling.dto.Debt;
import com.math040.gambling.service.DebtService;

@Controller
@RequestMapping("/debt")
public class DebtController {
	@Autowired
	private DebtService debtService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Debt> list(){
		return debtService.findAll();
	}
	
	/**
	 *   
	 * @return
	 */  
	@RequestMapping(value="/put", method = RequestMethod.GET)
	@ResponseBody
	public Long put(){
		Debt debt = new Debt();
		debt.setTitle("first debt");
		return debtService.save(debt);
	}

}

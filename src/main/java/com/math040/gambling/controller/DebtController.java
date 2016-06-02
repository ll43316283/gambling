package com.math040.gambling.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.math040.gambling.GamblingException;
import com.math040.gambling.service.DebtService;
import com.math040.gambling.service.TransactionService;
import com.math040.gambling.service.UserService;
import com.math040.gambling.vo.Debt;
import com.math040.gambling.vo.Transaction;
import com.math040.gambling.vo.User;

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
		return new ModelAndView("debts_in_process","debts",debtService.findCurrentSeasonInProcss());
	}
	
	@RequestMapping(value="/endList", method = RequestMethod.GET) 
	public ModelAndView endList() throws GamblingException{ 
		return new ModelAndView("debts_end","debts",debtService.findCurrentSeasonEnded());
	}
	@RequestMapping(value="/cancelList", method = RequestMethod.GET) 
	public ModelAndView cancelList() throws GamblingException{ 
		return new ModelAndView("debts_cancel","debts",debtService.findCurrentSeasonCanceled());
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
		model.put("debt", debt);
		model.put("transList", transList); 
		if(Debt.STATUS_OPEN.equals(debt.getStatus())){  
			Map<String, List<Integer>> predicts = transService.getAvailablePredictAmounts(debt); 
			model.put("predicts", predicts);
			model.put("viewModel", getDebtViewModel(transList, debt)); 
			return "open_debt_info";
		}
		return "end_debt_info";
	}
	
	@RequestMapping(value="/{id}/end",method=RequestMethod.POST)
	public String end(@PathVariable Long id, Debt debt)throws GamblingException{
		debtService.end(debt);
		return "redirect:/debt/endList";
	}
	
	@RequestMapping(value="/{id}/cancel",method=RequestMethod.POST)
	public String cancel(@PathVariable Long id, Debt debt)throws GamblingException{
		debtService.cancel(debt);
		return "redirect:/debt/cancelList";
	}
	 
	private final static String DEBT_VIEW_VIEW_MODEL="view";
	private final static String DEBT_VIEW_WAGER_MODEL="wager";
	
	private String getDebtViewModel(List<Transaction> transList,Debt debt){
		User current = userService.getCurrent();
		if(debt.getDealer().getUserName().equals(current.getUserName())){
			return DEBT_VIEW_VIEW_MODEL;
		}
		if(debt.getDeadline().before(new Date())){
			return DEBT_VIEW_VIEW_MODEL;
		}
		if(!CollectionUtils.isEmpty(transList)){ 
			for(Transaction trans:transList){
				if(trans.getGambler().getUserName().equals(current.getUserName())){
					return DEBT_VIEW_VIEW_MODEL;
				}
			} 
		}
		return DEBT_VIEW_WAGER_MODEL;
	}
}

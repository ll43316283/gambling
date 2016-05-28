package com.math040.gambling.controller;
 
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;  
import com.math040.gambling.GamblingException;
import com.math040.gambling.service.UserStatisticsService;  

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController{
	
	public static Logger logger = LoggerFactory.getLogger(StatisticsController.class);
	 
	@Autowired
	private UserStatisticsService statisticsService;
	
	@RequestMapping(value="rank", method = RequestMethod.GET) 
	public String rank() throws GamblingException{  
		return "rank";
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String doStatistics() throws GamblingException{
		statisticsService.doStatistics();
		return "redirect:/statistics/rank";
	}
}

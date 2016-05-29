package com.math040.gambling.controller;
 
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.UserStatistics;
import com.math040.gambling.service.UserStatisticsService;  

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController{
	
	public static Logger logger = LoggerFactory.getLogger(StatisticsController.class);
	 
	@Autowired
	private UserStatisticsService statisticsService;
	
	@RequestMapping(value="rank", method = RequestMethod.GET) 
	public ModelAndView rank() throws GamblingException{ 
		return new ModelAndView("rank","userStatisticses",statisticsService.findAllInCurrentSeasonOrderByRanking());
	}
	
	@RequestMapping(value="rank.json", method = RequestMethod.GET) 
	@ResponseBody
	public List<UserStatistics>  rankJson() throws GamblingException{ 
		return statisticsService.findAllInCurrentSeasonOrderByRanking();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String doStatistics() throws GamblingException{
		statisticsService.doStatistics(); 
		return "redirect:/statistics/rank";
	}
}

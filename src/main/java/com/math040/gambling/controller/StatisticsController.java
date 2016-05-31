package com.math040.gambling.controller;
 
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Rank;
import com.math040.gambling.dto.Transaction;
import com.math040.gambling.dto.UserStatistics;
import com.math040.gambling.service.TransactionService;
import com.math040.gambling.service.UserStatisticsService;  

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController{
	
	public static Logger logger = LoggerFactory.getLogger(StatisticsController.class);
	 
	@Autowired
	private UserStatisticsService statisticsService;
	
	@Autowired
	private TransactionService transService;
	
	@RequestMapping(value="rank", method = RequestMethod.GET) 
	public ModelAndView rank() throws GamblingException{  
		List<UserStatistics> usList = statisticsService.findAllInCurrentSeasonOrderByRanking();
		List<Rank> ranks = new ArrayList<>();
		for(UserStatistics us : usList){
			ranks.add(new Rank(us));
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
        String json="";
		try {
			json = mapper.writeValueAsString(ranks);
		} catch (JsonGenerationException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} catch (JsonMappingException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} catch (IOException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} 
		return new ModelAndView("rank","ranks",json);
	}
	
	@RequestMapping(value="scoreLine", method = RequestMethod.GET)  
	public ModelAndView scoreLine() throws GamblingException{
		List<Transaction> allscores = transService.findClosedTransBySeason();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
        String json="";
		try {
			json = mapper.writeValueAsString(allscores);
		} catch (JsonGenerationException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} catch (JsonMappingException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} catch (IOException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} 
		return new ModelAndView("scoreLine","allscores",json);
	}
	
	@RequestMapping(value="rank.json", method = RequestMethod.GET) 
	@ResponseBody
	public List<Rank>  rankJson() throws GamblingException{ 
		List<UserStatistics> usList = statisticsService.findAllInCurrentSeasonOrderByRanking();
		List<Rank> ranks = new ArrayList<>();
		for(UserStatistics us : usList){
			ranks.add(new Rank(us));
		}
		return ranks;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String doStatistics() throws GamblingException{
		statisticsService.doStatistics(); 
		return "redirect:/statistics/rank";
	}
}

package com.math040.gambling.controller;
 
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.BetScore;
import com.math040.gambling.dto.Rank;
import com.math040.gambling.dto.UserScores;
import com.math040.gambling.service.DebtService;
import com.math040.gambling.service.TransactionService;
import com.math040.gambling.service.UserStatisticsService;
import com.math040.gambling.vo.Debt;
import com.math040.gambling.vo.Transaction;
import com.math040.gambling.vo.UserStatistics;  

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController{
	
	public static Logger logger = LoggerFactory.getLogger(StatisticsController.class);
	 
	@Autowired
	private UserStatisticsService statisticsService;
	
	@Autowired
	private TransactionService transService;
	
	@Autowired
	private DebtService debtService;
	
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
	
	private UserScores getUserScoreByUserName(List<UserScores> userScoresList,UserScores user){
		if(CollectionUtils.isEmpty(userScoresList)){
			return null;
		}
		for(UserScores temp:userScoresList){
			if(user.equals(temp)){
				return temp;
			}
		}
		return null;
	}
	
	@RequestMapping(value="scoreLine", method = RequestMethod.GET)  
	public ModelAndView scoreLine() throws GamblingException{
		
		List<UserScores> userScoresList = getAllUserScores(); 
		List<Integer> sequenceBetIdList = getSequenceBetIdList();
		List<String> nameList = getAllNames(userScoresList);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
        String allData="";
        String sequenceBetIds="";
        String names="";
		try {
			allData = mapper.writeValueAsString(userScoresList);
			sequenceBetIds = mapper.writeValueAsString(sequenceBetIdList);
			names = mapper.writeValueAsString(nameList);
		} catch (JsonGenerationException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} catch (JsonMappingException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} catch (IOException e) {
			throw new GamblingException(GamblingException.JACKSON_TRANSFER_ERROR);
		} 
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("allscores",allData);
		model.put("sequenceBetIds",sequenceBetIds);
		model.put("names",names);
		model.put("nameList",nameList);
		return new ModelAndView("scoreLine",model);
	}
	
	private List<String> getAllNames(List<UserScores> userScoresList ){
		List<String> result = new ArrayList<>();
		if(CollectionUtils.isEmpty(userScoresList)){
			return result;
		}
		for(UserScores user:userScoresList){
			result.add(user.getName());
		}
		return result;
	}
	
	private List<Integer> getSequenceBetIdList() throws GamblingException {
		List<Debt> debts = debtService.findCurrentSeasonEnded();
		List<Integer> sequenceBetIdList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(debts)){
			for(Debt d : debts){
				sequenceBetIdList.add(d.getId().intValue());
			}
		}
		return sequenceBetIdList;
	}

	private List<UserScores> getAllUserScores() throws GamblingException {
		List<Transaction> allscores = transService.findClosedTransBySeason();
		List<UserScores> userScoresList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(allscores)){
			for(Transaction trans:allscores){
				UserScores user = new UserScores(trans.getGambler().getUserName());
				if(userScoresList.contains(user)){
					user = getUserScoreByUserName(userScoresList, user);
				}else{
					userScoresList.add(user);
				}
				BetScore betscore = new BetScore(
						trans.getDebt().getId(),trans.getDebt().getTitle(), trans.getAmount());
				user.getDatasets().add(betscore);
			}
		}
		return userScoresList;
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

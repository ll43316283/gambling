package com.math040.gambling.service.impl;
 
 
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.UserStatistics;
import com.math040.gambling.repository.TransactionRepository;
import com.math040.gambling.repository.UserRepository;
import com.math040.gambling.repository.UserStatisticsRepository;
import com.math040.gambling.service.SeasonService;
import com.math040.gambling.service.UserStatisticsService;

@Service
@Transactional
public class UserStatisticsServiceImpl implements UserStatisticsService {
	private static Logger logger = LoggerFactory.getLogger(UserStatisticsServiceImpl.class);
	
	@Autowired
	UserStatisticsRepository usDao;
	
	@Autowired
	SeasonService seasonService; 
	
	@Autowired
	UserRepository userDao;
	
	@Autowired
	TransactionRepository transDao;
	
	@Override
	public void doStatistics() throws GamblingException{
		int season = seasonService.getCurrent().getSeason();
	    doStatisticsAmounts(season);
	    List<UserStatistics> usList = usDao.findBySeason(season); 
		if(CollectionUtils.isEmpty(usList)){
			return;
		}
	    doStatisticsWinRate(season,usList);
	    doStatisticsTitles(season,usList);
	}


	private void doStatisticsAmounts(int season) throws GamblingException {
		List<Object[]> userAmounts = usDao.findUserAmountsBySeason(season);
		if(CollectionUtils.isEmpty(userAmounts)){
			return;
		} 
		for(Object[] objs:userAmounts){
			if(objs==null || objs.length!=2  ){
				throw new GamblingException(GamblingException.USER_STATIS_CANNOT_GET_TOTAL_AMOUNTS);
			}
			Long gamblerId =0l ;
			Long winningAmounts;
			try{
			 gamblerId = (Long) objs[0];
			 winningAmounts = (Long) objs[1];
			}catch(Exception ex){
				logger.error(" doStatistics error: " + ex.toString());
				throw new GamblingException(GamblingException.USER_STATIS_CANNOT_GET_TOTAL_AMOUNTS);
			}
			UserStatistics us = usDao.findBySeasonAndGambler_id(season, gamblerId); 
			if(us == null){
				UserStatistics newUs = new UserStatistics();
				newUs.setAmount(winningAmounts.intValue());
				newUs.setGambler(userDao.findOne(gamblerId));
				newUs.setSeason(season);
				newUs.setUpdateDate(new Date()); 
				usDao.save(newUs);
			}else{ 
				us.setAmount(winningAmounts.intValue());
				us.setUpdateDate(new Date());
			}
		}
	}
	
	private void doStatisticsWinRate(int season,List<UserStatistics> usList){ 
		for(UserStatistics us:usList){
			Integer totalTrans = transDao.countTransBySeasonAndGambler(season, us.getGambler());
			Integer totalWinTrans = transDao.countWinTransBySeasonAndGambler(season, us.getGambler());
			if(totalTrans.equals(new Integer(0))){
				us.setWinningRate(0.0);
			}else{
				double winRate = totalWinTrans.doubleValue()/totalTrans.doubleValue();  
				us.setWinningRate(winRate);
			}  
			us.setUpdateDate(new Date()); 
		}
	}
	
	

	private void doStatisticsTitles(int season,List<UserStatistics> usList) {  
		for(UserStatistics us:usList){
			if(!CollectionUtils.isEmpty(us.getTitles())){
				us.getTitles().clear();
			} 
		}
		
		
	}
}

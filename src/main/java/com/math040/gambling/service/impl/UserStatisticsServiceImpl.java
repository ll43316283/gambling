package com.math040.gambling.service.impl;
 
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Title;
import com.math040.gambling.dto.UserStatistics;
import com.math040.gambling.repository.TitleRepository;
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
	
	@Autowired
	TitleRepository titleDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
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

	@Transactional(propagation=Propagation.NESTED)
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
	@Transactional(propagation=Propagation.NESTED)
	private void doStatisticsWinRate(int season,List<UserStatistics> usList){ 
		for(UserStatistics us:usList){
			Integer totalTrans = transDao.countTransBySeasonAndGamblerAndNotDealer(season, us.getGambler());
			Integer totalWinTrans = transDao.countWinTransBySeasonAndGamblerAndNotDealer(season, us.getGambler());
			if(totalTrans.equals(new Integer(0))){
				us.setWinningRate(0.0);
			}else{
				double winRate = totalWinTrans.doubleValue()/totalTrans.doubleValue();  
				us.setWinningRate(winRate);
			}  
			us.setUpdateDate(new Date()); 
			usDao.save(us);
		}
		
	}
	
	
	@Transactional(propagation=Propagation.NESTED)
	private void doStatisticsTitles(int season,List<UserStatistics> usList) {  
		for(UserStatistics us:usList){
			if(!CollectionUtils.isEmpty(us.getTitles())){
				us.setTitles(new ArrayList<>());
			} 
		} 
		setHighestAmountTitle(usList); 
		setHighestRateTitle(usList);
	}

	@Transactional(propagation=Propagation.NESTED)
	private void setHighestAmountTitle(List<UserStatistics> usList) {
		
		Collections.sort(usList, new Comparator<UserStatistics>(){ 
			@Override
			public int compare(UserStatistics o1, UserStatistics o2) { 
				return o2.getAmount()-o1.getAmount();
			}
		});
		
		Title title = titleDao.findByCode(Title.TITLE_CODE_RICHEST_PERSON)  ; 
		
		if(usList.get(0).getAmount()!=0){ 
			usList.get(0).getTitles().add(title);
			usDao.save(usList.get(0));
			int i =0;
			int max = usList.size()-1;
			boolean end = false;
			do{
				if(usList.get(i).getAmount() == usList.get(i+1).getAmount()){
					//UserStatistics us = usDao.findBySeasonAndGambler_id(usList.get(i).getSeason(), usList.get(i).getGambler().getId());
					//us.getTitles().add(title);
					usDao.save(usList.get(i+1));
				}else{
					end = true;
				}
				i++;
			}while( !end && i< max-1);
		} 
	}
	@Transactional(propagation=Propagation.NESTED)
	private void setHighestRateTitle(List<UserStatistics> usList) {
		
		Collections.sort(usList, new Comparator<UserStatistics>(){ 
			@Override
			public int compare(UserStatistics o1, UserStatistics o2) { 
				Double t2 = o2.getWinningRate()==null?0:o2.getWinningRate();
				Double t1 = o1.getWinningRate()==null?0:o1.getWinningRate();
				return t2.compareTo(t1);
			}
		});
		
		Title title = titleDao.findByCode(Title.TITLE_CODE_HIGHEST_RATE)  ; 
		
		if(usList.get(0).getWinningRate()!=null 
				&& usList.get(0).getWinningRate().compareTo(0.0)!=0){ 
			usList.get(0).getTitles().add(title);
			usDao.save(usList.get(0));
			int i =0;
			int max = usList.size()-1;
			boolean end = false;
			do{
				if(usList.get(i).getWinningRate().equals(usList.get(i+1).getWinningRate())){
					//UserStatistics us = usDao.findBySeasonAndGambler_id(usList.get(i).getSeason(), usList.get(i).getGambler().getId());
					//us.getTitles().add(title);
					usDao.save(usList.get(i+1));
				}else{
					end = true;
				}
				i++;
			}while( !end && i< max-1);
		}
	
	}


	 
}

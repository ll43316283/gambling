package com.math040.gambling.service;
 

import org.junit.Assert; 
import org.junit.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.GamblingException;
import com.math040.gambling.config.JpaConfig;
import com.math040.gambling.dto.Debt;
import com.math040.gambling.dto.Transaction;
import com.math040.gambling.dto.User;
import com.math040.gambling.dto.UserStatistics;
import com.math040.gambling.repository.DebtRepository;
import com.math040.gambling.repository.TransactionRepository;
import com.math040.gambling.repository.UserStatisticsRepository;

import config.TestBasedConfig; 
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class UserStatisticsServiceTest extends BaseTest {
	 
	
	@Autowired
	private UserStatisticsService usService;
	
	@Autowired
	TransactionRepository transDao;
	
	@Autowired
	DebtRepository debtDao;
	  
	@Autowired
	private UserStatisticsRepository usDao;
	
	@Autowired
	private TransactionService transService;
 
	@Autowired
	private DebtService debtService;
	 
	 
	
	@Rollback
	@Test
	public void testDoStatisticsAmountsWhenOneWin29AnotherLose23() throws GamblingException{
		Debt debt = initDebt(); 
		User gambler = initGambler(); 
		User gambler2 = initGambler2(); 
		
		Transaction trans1 = new Transaction();
		trans1.setDebt(debt);
		trans1.setGambler(gambler);
		trans1.setPredict(Transaction.PREDICT_YES);
		trans1.setAmount(29);
		transService.create(trans1);  
		Transaction trans2 = new Transaction();
		trans2.setDebt(debt);
		trans2.setGambler(gambler2);
		trans2.setPredict(Transaction.PREDICT_NO);
		trans2.setAmount(23);
		transService.create(trans2); 
		  
		debt.setResult(Debt.RESULT_YES);
		debtService.end(debt);   
		usService.doStatistics();
		
		
		
		UserStatistics liang = usDao.findByGamblerAndSeason(gambler, debt.getSeason());
		UserStatistics liang2 = usDao.findByGamblerAndSeason(gambler2, debt.getSeason());
		UserStatistics admin = usDao.findByGamblerAndSeason(debt.getDealer(), debt.getSeason());
		
		Assert.assertEquals(29,liang.getAmount());
		Assert.assertEquals(-23,liang2.getAmount());
		Assert.assertEquals(-6,admin.getAmount());
		
		
	}
	
	
}

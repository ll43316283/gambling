package com.math040.gambling.service;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
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
import com.math040.gambling.dto.Season;
import com.math040.gambling.dto.User;
import com.math040.gambling.repository.SeasonRepository;
import com.math040.gambling.util.DateUtil;

import config.TestBasedConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class BaseTest {
	public final static int TEST_SEASON =1000;
	@Autowired
	private DebtService debtService;
	
	@Autowired
	private UserService userService;
	
	@Rollback
	public User initGambler() throws GamblingException {
		User gambler = new User();
		gambler.setUserName("liang");
		gambler.setPassword("123");
		gambler.setRole(User.ROLE_USER); 
		return userService.save(gambler);
	}
	
	@Rollback
	public User initGambler2() throws GamblingException {
		User gambler = new User();
		gambler.setUserName("liang2");
		gambler.setPassword("123");
		gambler.setRole(User.ROLE_USER); 
		return userService.save(gambler);
	}
	
	@Rollback
	public Debt initDebt() throws GamblingException { 
		Debt debt = new Debt();
		debt.setTitle("first test debt");
		User user = userService.findByUserName("admin");
		debt.setDealer(user); 
		try {
			debt.setDeadline(DateUtil.parse("9999-1-1 12:01"));
		} catch (ParseException e) { 
		}
		return debtService.create(debt);
	}
	
	@Rollback
	public Debt initDeadLineInValidDebt() throws GamblingException {
		Debt debt = new Debt();
		debt.setTitle("first test debt");
		User user = userService.findByUserName("admin");
		debt.setDealer(user); 
		debt.setDeadline(new Date());
		return debtService.create(debt);
	}
	
	@Rollback
	public Debt initDebt2() throws GamblingException {
		Debt debt = new Debt();
		debt.setTitle("second test debt");
		User user = userService.findByUserName("admin");
		debt.setDealer(user); 
		try {
			debt.setDeadline(DateUtil.parse("9999-1-1 12:01"));
		} catch (ParseException e) { 
		}
		return debtService.create(debt);
	}
	
	@Autowired
	private SeasonService seasonService;
	@Autowired
	private SeasonRepository seasonDao;
	@Before
	@Rollback
	public void setCurrentSeasonTo1000() throws GamblingException{
		Season season = seasonService.getCurrent();
		season.setActive(Season.ACTIVE_N);
		Season s1 = new Season();
		s1.setActive(Season.ACTIVE_Y);
		s1.setSeason(TEST_SEASON);
		seasonDao.save(s1);
	}
	
	@Test
	public void testTrue(){
		Assert.assertTrue(true);
	}

}

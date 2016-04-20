package com.math040.gambling.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import com.math040.gambling.dto.User;
import com.math040.gambling.dto.UserStatistics;

import config.TestBasedConfig; 
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class UserStatisticsServiceTest {
	 
	
	@Autowired
	private UserStatisticsService usService;
	 
	@Autowired
	private UserService userService;
	
	@Test
	@Rollback
	public void testSave()throws GamblingException{
		UserStatistics us = new UserStatistics();
		User gambler = initGambler(); 
		us.setWinningRate(11.123);
		us.setGambler(gambler);
		UserStatistics savedUs = usService.create(us);
		System.out.println(savedUs.getWinningRate());
//		Assert.assertEquals(expected, actual);
	}
	
	@Rollback
	private User initGambler() throws GamblingException {
		User gambler = new User();
		gambler.setUserName("liang");
		gambler.setPassword("123");
		gambler.setRole(User.ROLE_USER); 
		return userService.save(gambler);
	}
}

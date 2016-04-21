package com.math040.gambling.service;

import java.util.Date;

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

import config.TestBasedConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class BaseTest {
	
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
		debt.setDeadline(new Date());
		return debtService.create(debt);
	}

}

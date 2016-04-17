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
import com.math040.gambling.dto.Transaction;
import com.math040.gambling.dto.User;

import config.TestBasedConfig; 
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class TransactionServiceTest {
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@Autowired
	private TransactionService transService;
	
	@Autowired
	private DebtService debtService;
	
	@Autowired
	private UserService userService;
	   
	@Test
	@Rollback
	public void testCreateTrans() throws GamblingException { 
		Debt debt = initDebt();
		User user = userService.findByUserName("admin"); 
		Transaction trans = new Transaction();
		trans.setDebt(debt);
		trans.setGambler(user);
		trans.setPredict(Transaction.PREDICT_YES);
		trans.setAmount(29);
		Transaction savedTrans = transService.create(trans);
		Assert.assertNotNull(savedTrans.getId());
		Assert.assertEquals(29, savedTrans.getAmount());
		Assert.assertEquals(trans.getPredict(), savedTrans.getPredict());
	}
	 
	
	@Test
	@Rollback
	public void testCreateTrans_with_2_same_amount_predict_throw_amount_not_correct_exception() throws GamblingException { 
		Debt debt = initDebt();
		User user = userService.findByUserName("admin"); 
		Transaction trans1 = new Transaction();
		trans1.setDebt(debt);
		trans1.setGambler(user);
		trans1.setPredict(Transaction.PREDICT_YES);
		trans1.setAmount(29);
		transService.create(trans1);
		thrown.expect(GamblingException.class);
		thrown.expectMessage(GamblingException.TRANS_AMOUNT_NOT_CORRECT); 
		Transaction trans2 = new Transaction();
		trans2.setDebt(debt);
		trans2.setGambler(user);
		trans2.setPredict(Transaction.PREDICT_YES);
		trans2.setAmount(29);
		transService.create(trans1);
	}
	
	@Rollback
	private Debt initDebt() throws GamblingException {
		Debt debt = new Debt();
		debt.setTitle("first test debt");
		User user = userService.findByUserName("admin");
		debt.setDealer(user); 
		debt.setDeadline(new Date());
		return debtService.create(debt);
	}

}

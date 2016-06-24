package com.math040.gambling.service;
 

import java.util.List;

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
import com.math040.gambling.vo.Debt;
import com.math040.gambling.vo.User;

import config.TestBasedConfig;
import config.TestJpaConfig; 
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,TestJpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class DebtServiceTest extends BaseTest{
	@Rule  
	public ExpectedException thrown= ExpectedException.none();
	
	@Autowired
	private DebtService debtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SeasonService seasonService;
	    
	@Test
	@Rollback(true)
	public void testCreateDebtThrowGamblingException_USER_ID_SHOULD_NOT_NULL() throws GamblingException {
		thrown.expect(GamblingException.class);
		thrown.expectMessage(GamblingException.DEBT_USER_ID_SHOULD_NOT_NULL); 
		Debt debt = new Debt(); 
		debtService.create(debt);
		thrown.expectMessage(GamblingException.DEBT_TITLE_SHOULD_NOT_NULL); 
		User user = userService.findByUserName("admin");
		debt.setDealer(user);
		debtService.create(debt);
	}
	
	@Test
	@Rollback(true)
	public void testCreateDebtThrowGamblingException_DEADLINE_SHOULD_NOT_NULL() throws GamblingException {
		thrown.expect(GamblingException.class); 
		Debt debt = new Debt(); 
		debtService.create(debt);
		thrown.expectMessage(GamblingException.DEBT_DEADLINE_SHOULD_NOT_NULL); 
		User user = userService.findByUserName("admin");
		debt.setDealer(user);
		debtService.create(debt);
	}
	 
	@Test
	@Rollback(true)
	public void testCreateDebt() throws GamblingException {
		Debt debtSaved = initDebt();
		Assert.assertNotNull(debtSaved.getId());
		Assert.assertNotNull(debtSaved.getCreateDate());
		Assert.assertEquals(TEST_SEASON, debtSaved.getSeason());
		Assert.assertEquals(Debt.STATUS_OPEN, debtSaved.getStatus());
		Assert.assertEquals(seasonService.getCurrent().getSeason(), debtSaved.getSeason());
	}
	
	
	@Test
	@Rollback(true)
	public void testCancleDebtThrowGamblingException_CANNOT_CANCLE_A_CLOSED_DEBT() throws GamblingException { 
		Debt savedDebt = initDebt();
		savedDebt.setStatus(Debt.STATUS_CLOSE);
		thrown.expect(GamblingException.class); 
		thrown.expectMessage(GamblingException.DEBT_CANNOT_CANCLE_A_CLOSED_DEBT);
	    debtService.cancel(savedDebt);
	}
	
	@Test
	@Rollback(true)
	public void testCancleDebt() throws GamblingException { 
		Debt savedDebt = initDebt();  
	    debtService.cancel(savedDebt);
	    Debt canceledDebt = debtService.findById(savedDebt.getId());
	    Assert.assertEquals(Debt.STATUS_CANCEL, canceledDebt.getStatus());
	}
 
	
	@Test
	@Rollback(true)
	public void testEndDebtThrowGamblingException_RESULT_SHOULD_NOT_NULL() throws GamblingException { 
		Debt savedDebt = initDebt(); 
		thrown.expect(GamblingException.class); 
		thrown.expectMessage(GamblingException.DEBT_RESULT_SHOULD_NOT_NULL_WHEN_END_DEBT);
	    debtService.end(savedDebt);
	}
	
	@Test
	@Rollback(true)
	public void testEndDebtThrowGamblingException_DEBT_HAS_BEEN_CANCELED_OR_CLOSED() throws GamblingException { 
		Debt savedDebt = initDebt(); 
		savedDebt.setResult(Debt.RESULT_YES);
		savedDebt.setStatus(Debt.STATUS_CLOSE); 
		thrown.expect(GamblingException.class); 
		thrown.expectMessage(GamblingException.DEBT_IS_CLOSED_OR_CANCELED);
	    debtService.end(savedDebt);
	}
	
	@Test
	@Rollback(true)
	public void testEndDebt() throws GamblingException { 
		Debt savedDebt = initDebt();  
		savedDebt.setResult(Debt.RESULT_YES);
	    debtService.end(savedDebt);
	    Debt endedDebt = debtService.findById(savedDebt.getId());
	    Assert.assertEquals(Debt.STATUS_CLOSE, endedDebt.getStatus());
	    Assert.assertEquals(Debt.RESULT_YES, endedDebt.getResult());
	    Assert.assertNotNull(endedDebt.getEndDate());
	}

	
	@Test
	@Rollback(true)
	public void testFindCurrentSeasonInProcss() throws GamblingException { 
		 initDebt();  
	    List<Debt> debtList = debtService.findCurrentSeasonInProcss();
	    Assert.assertEquals(1, debtList.size()); 
	}
}

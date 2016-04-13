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
import com.math040.gambling.config.JpaConfig;
import com.math040.gambling.dto.Debt;
import com.math040.gambling.repository.DebtRepository;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class DebtServiceTest {
	@Autowired
	private DebtRepository debtRepository;
	 
	@Test
	@Rollback(true)
	public void testSave(){
		Debt debt = new Debt();
		debt.setTitle("testDebt");
		Debt debtsaved = debtRepository.save(debt);
		Debt debtFind = debtRepository.findOne(debtsaved.getId()); 
		Assert.assertEquals("testDebt", debtFind.getTitle());
	}
	
}

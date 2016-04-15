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
import com.math040.gambling.dto.DebtDto;

import config.TestBasedConfig; 
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class DebtServiceTest {
	@Autowired
	private DebtService debtService;
	   
	@Test
	@Rollback(true)
	public void testSave(){
		DebtDto debt = new DebtDto();
		debt.setTitle("testDebt");
		Long id = debtService.save(debt);
		DebtDto debtFind = debtService.findById(id); 
		Assert.assertEquals("testDebt", debtFind.getTitle());
	}
	
}

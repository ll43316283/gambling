package com.math040.gambling.service;

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
import com.math040.gambling.dto.Title;

import config.TestBasedConfig;
import config.TestJpaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,TestJpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class TitleServiceTest {
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@Autowired
	private TitleService titleService;
	 
	
	@Rollback
	@Test
	public void testCreateTitleThrowTitleAndDescShouldNotBeNull() throws GamblingException{
		thrown.expect(GamblingException.class);
		thrown.expectMessage(GamblingException.TITLE_TITLE_AND_DESC_SHOULD_NOT_BE_NULL);
		Title title = new Title();
		titleService.save(title);
		
		thrown.expect(GamblingException.class);
		thrown.expectMessage(GamblingException.TITLE_TITLE_AND_DESC_SHOULD_NOT_BE_NULL);
		title.setTitle("king");
		titleService.save(title);
	}
	
	@Rollback
	@Test
	public void testCreateTitleThrowTitleCodeShouldNotBeNull() throws GamblingException{
		thrown.expect(GamblingException.class);
		thrown.expectMessage(GamblingException.TITLE_TITLE_CODE_SHOULD_NOT_BE_NULL);
		Title title = new Title();
		title.setTitle("king");
		title.setDescription("this title is the king of all the gamblers");
		titleService.save(title);
		 
	}
	
	@Rollback
	@Test
	public void testCreateTitleThrowTitleCodeShouldBeUnique() throws GamblingException{
		thrown.expect(GamblingException.class);
		thrown.expectMessage(GamblingException.TITLE_TITLE_CODE_SHOULD_BE_UNIQUE);
		Title title = new Title();
		title.setTitle("king");
		title.setDescription("this title is the king of all the gamblers");
		title.setCode(Title.TITLE_CODE_RICHEST_PERSON);
		titleService.save(title);
		 
	}
	
	@Rollback
	@Test
	public void testCreateTitle() throws GamblingException{ 
		Title title = new Title();
		title.setTitle("king");
		title.setDescription("this title is the king of all the gamblers");
		title.setCode("TT");
		titleService.save(title); 
		Assert.assertNotNull(title.getId()); 
	}
	 
}

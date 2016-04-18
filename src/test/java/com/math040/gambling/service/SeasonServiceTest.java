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
import com.math040.gambling.config.JpaConfig;
import com.math040.gambling.dto.Season;
import com.math040.gambling.repository.SeasonRepository;

import config.TestBasedConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestBasedConfig.class,JpaConfig.class})
@Transactional
@TestExecutionListeners(                
	    { DependencyInjectionTestExecutionListener.class,  
	    	TransactionalTestExecutionListener.class })  
public class SeasonServiceTest {
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@Autowired
	private SeasonService seasonService;
	
	@Autowired
	private SeasonRepository seasonDao;
	
	@Rollback
	@Test
	public void testInitSeason() throws GamblingException{
		seasonDao.deleteAll();
		Season season = seasonService.getCurrent();
		Assert.assertEquals(1, season.getSeason());
		Assert.assertEquals(Season.ACTIVE_Y, season.getActive());
	}
	
	@Rollback
	@Test
	public void testGetActiveSeason() throws GamblingException{
		seasonDao.deleteAll();
		Season s1 = new Season();
		s1.setActive(Season.ACTIVE_N);
		s1.setSeason(1);
		seasonDao.save(s1);
		Season s2 = new Season();
		s2.setActive(Season.ACTIVE_Y);
		s2.setSeason(2);
		seasonDao.save(s2);
		Season season = seasonService.getCurrent();
		Assert.assertEquals(2, season.getSeason());
		Assert.assertEquals(Season.ACTIVE_Y, season.getActive());
	}
	
	@Rollback
	@Test
	public void testGetActiveSeasonThrow_no_avaliable_season() throws GamblingException{
		seasonDao.deleteAll();
		Season s1 = new Season();
		s1.setActive(Season.ACTIVE_N);
		s1.setSeason(1);
		seasonDao.save(s1);
		Season s2 = new Season();
		s2.setActive(Season.ACTIVE_N);
		s2.setSeason(2);
		seasonDao.save(s2); 
		thrown.expect(GamblingException.class);
		thrown.expectMessage(GamblingException.SEASON_NO_AVAILABLE_SEASON);
		seasonService.getCurrent(); 
	}
}

package com.math040.gambling.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Season;
import com.math040.gambling.repository.SeasonRepository;
import com.math040.gambling.service.SeasonService; 

@Service
public class SeasonServiceImpl implements SeasonService {
	@Autowired
	SeasonRepository seasonDao;

	@Override
	public Season getCurrent() throws GamblingException { 
		List<Season> seasons = seasonDao.findByActive(Season.ACTIVE_Y);
		if(CollectionUtils.isEmpty(seasons)){
			Page<Season> seasonPage = seasonDao.findAll(new PageRequest(0, 1,new Sort(Sort.Direction.DESC,"season")));
			Season season = new Season();
			season.setActive(Season.ACTIVE_Y);
			season.setStartDate(new Date());
			if(seasonPage==null || CollectionUtils.isEmpty(seasonPage.getContent())){  
				season.setSeason(1);  
			}else{
				Season lastSeason = seasonPage.getContent().get(0) ;
				season.setSeason(lastSeason.getSeason()+1); 
			} 
			return seasonDao.save(season); 
		}
		return seasons.get(0);
	}
	 
	 
	 
}

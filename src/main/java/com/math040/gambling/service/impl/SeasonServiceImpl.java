package com.math040.gambling.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
			seasons = seasonDao.findAll();
			if(CollectionUtils.isEmpty(seasons)){
				Season season = new Season();
				season.setActive(Season.ACTIVE_Y);
				season.setStartDate(new Date());
				season.setSeason(1); 
				return seasonDao.save(season);
			}
			throw new GamblingException(GamblingException.SEASON_NO_AVAILABLE_SEASON);
		}
		return seasons.get(0);
	}
	 
	 
	 
}

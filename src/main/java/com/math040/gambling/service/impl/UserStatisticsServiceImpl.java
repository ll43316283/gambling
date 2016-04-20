package com.math040.gambling.service.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.math040.gambling.GamblingException; 
import com.math040.gambling.dto.UserStatistics; 
import com.math040.gambling.repository.UserStatisticsRepository; 
import com.math040.gambling.service.UserStatisticsService;

@Service
@Transactional
public class UserStatisticsServiceImpl implements UserStatisticsService {
	@Autowired
	UserStatisticsRepository usDao;

	@Override
	public UserStatistics create(UserStatistics us) throws GamblingException { 
		return usDao.save(us);
	}
	
	
}

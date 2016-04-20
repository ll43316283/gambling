package com.math040.gambling.service;
 
import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.UserStatistics;

public interface UserStatisticsService {
	 UserStatistics create(UserStatistics us)throws GamblingException;
}

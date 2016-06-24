package com.math040.gambling.service;
 
import java.util.List;

import com.math040.gambling.GamblingException;
import com.math040.gambling.vo.UserStatistics; 

public interface UserStatisticsService { 
	 void doStatistics()throws GamblingException;
	 List<UserStatistics> findAllInCurrentSeason() throws GamblingException;
	 List<UserStatistics> findAllInCurrentSeasonOrderByRanking() throws GamblingException;
}

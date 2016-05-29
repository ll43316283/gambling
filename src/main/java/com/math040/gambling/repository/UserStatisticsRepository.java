package com.math040.gambling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.dto.Debt;
import com.math040.gambling.dto.User;
import com.math040.gambling.dto.UserStatistics;
 

@Transactional
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long> {
	@Query("   select trans.gambler.id as userId, sum(trans.winAmount) as amounts "
			+ "  from Transaction trans where trans.debt.season=:season and trans.debt.status='"+Debt.STATUS_CLOSE+"'"
			+ "  group by gambler.id  ")
	List<Object[]> findUserAmountsBySeason(@Param("season")  int season);
	
	UserStatistics findBySeasonAndGambler_id(int season, Long gamblerId );
	
	UserStatistics findByGamblerAndSeason(User gambler, int season);
	
	List<UserStatistics> findBySeasonOrderByAmountDescWinningRateAsc(int season);
	
	List<UserStatistics> findBySeasonOrderByRankingAsc(int season);
}

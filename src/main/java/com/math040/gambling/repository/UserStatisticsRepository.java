package com.math040.gambling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.dto.UserStatistics;
 

@Transactional
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long> {

}

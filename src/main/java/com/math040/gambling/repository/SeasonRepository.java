package com.math040.gambling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.vo.Season; 

@Transactional
public interface SeasonRepository extends JpaRepository<Season, Long> { 
	List<Season> findByActive(String active);
}

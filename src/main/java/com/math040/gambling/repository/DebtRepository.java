package com.math040.gambling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.dto.Debt;

@Transactional
public interface DebtRepository extends JpaRepository<Debt, Long> {
	List<Debt> findBySeasonAndStatus(int season,String status);
}

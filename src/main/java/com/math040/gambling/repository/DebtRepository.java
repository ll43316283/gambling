package com.math040.gambling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.vo.Debt;

@Transactional
public interface DebtRepository extends JpaRepository<Debt, Long> {
	List<Debt> findBySeasonAndStatusOrderByEndDateAsc(int season,String status);
}

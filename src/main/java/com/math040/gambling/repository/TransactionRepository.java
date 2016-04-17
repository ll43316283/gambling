package com.math040.gambling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.dto.Transaction; 

@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> { 
	List<Transaction> findByDebt_IdAndPredict(Long debtId,String predict);
}

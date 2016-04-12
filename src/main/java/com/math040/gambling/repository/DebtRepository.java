package com.math040.gambling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.math040.gambling.dto.Debt;

public interface DebtRepository extends JpaRepository<Debt, Long> {

}

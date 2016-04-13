package com.math040.gambling.service;

import java.util.List;

import com.math040.gambling.dto.Debt;

public interface DebtService {
	public List<Debt> findAll();
	public Long save(Debt debt);
	public Debt findById(Long id);
}

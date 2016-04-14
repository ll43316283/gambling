package com.math040.gambling.service;

import java.util.List;

import com.math040.gambling.dto.DebtDto;

public interface DebtService {
	public List<DebtDto> findAll();
	public Long save(DebtDto debt);
	public DebtDto findById(Long id);
}

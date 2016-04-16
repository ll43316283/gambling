package com.math040.gambling.service;

import java.util.List;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Debt;

public interface DebtService {
	public List<Debt> findAll();
	public Debt create(Debt debt) throws GamblingException;
	public Debt findById(Long id);
	public Debt cancel(Debt debt)throws GamblingException;
	public Debt end(Debt debt)throws GamblingException;
}

package com.math040.gambling.service;

import java.util.List;

import com.math040.gambling.GamblingException;
import com.math040.gambling.vo.Debt;

public interface DebtService {
	 List<Debt> findAll();
	 List<Debt> findCurrentSeasonInProcss() throws GamblingException;
	 Debt create(Debt debt) throws GamblingException;
	 Debt findById(Long id);
	 Debt cancel(Debt debt)throws GamblingException;
	 Debt end(Debt debt)throws GamblingException; 
	 List<Debt> findCurrentSeasonEnded() throws GamblingException;
	 List<Debt> findCurrentSeasonCanceled() throws GamblingException;
}

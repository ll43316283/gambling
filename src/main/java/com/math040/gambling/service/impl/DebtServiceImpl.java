package com.math040.gambling.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.math040.gambling.dto.Debt;
import com.math040.gambling.repository.DebtRepository;
import com.math040.gambling.service.DebtService;

@Service
public class DebtServiceImpl implements DebtService {
	@Autowired
	DebtRepository debtDao;
	
	public List<Debt> findAll(){
		return debtDao.findAll();
	}
	
	public Long save(Debt debt){
		return debtDao.save(debt).getId();
	}
}

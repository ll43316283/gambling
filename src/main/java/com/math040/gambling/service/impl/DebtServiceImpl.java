package com.math040.gambling.service.impl;

import java.util.Date;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Debt;
import com.math040.gambling.repository.DebtRepository;
import com.math040.gambling.service.DebtService;
import com.math040.gambling.service.SeasonService;
import com.math040.gambling.service.TransactionService;

@Service
@Transactional
public class DebtServiceImpl implements DebtService {
	@Autowired
	DebtRepository debtDao;
	
	@Autowired
	SeasonService seasonService;
	
	@Autowired
	TransactionService transactionService;
	
	public List<Debt> findAll(){
		return debtDao.findAll();
	}
	
	public Debt create(Debt debt)throws GamblingException{
		Assert.notNull(debt," debt should not null " );  
		if(debt.getDealer()==null || debt.getDealer().getId()==null){
			throw new GamblingException(GamblingException.DEBT_USER_ID_SHOULD_NOT_NULL);
		}
		if(debt.getDeadline()==null ){
			throw new GamblingException(GamblingException.DEBT_DEADLINE_SHOULD_NOT_NULL);
		}
		if(!StringUtils.hasText(debt.getTitle())){
			throw new GamblingException(GamblingException.DEBT_TITLE_SHOULD_NOT_NULL);
		}  
		debt.setStatus(Debt.STATUS_OPEN);
		debt.setCreateDate(new Date());
		debt.setSeason(seasonService.getCurrent().getSeason());
		return debtDao.save(debt);
	}
	
	public Debt cancel(Debt debt)throws GamblingException{
		Assert.notNull(debt," debt should not null " );  
		Assert.notNull(debt.getId()," debt should not null "); 
		Debt savedDebt = debtDao.findOne(debt.getId());
		if(Debt.STATUS_CLOSE.equalsIgnoreCase(savedDebt.getStatus())){
			throw new GamblingException(GamblingException.DEBT_CANNOT_CANCLE_A_CLOSED_DEBT);
		}
		savedDebt.setStatus(Debt.STATUS_CANCEL);
		savedDebt.setEndDate(new Date());
		return debtDao.save(savedDebt); 
	}
	
	public Debt end(Debt debt)throws GamblingException{
		Assert.notNull(debt," debt should not null " );  
		Assert.notNull(debt.getId()," debt should not null "); 
		if(!StringUtils.hasText(debt.getResult())){
			throw new GamblingException(GamblingException.DEBT_RESULT_SHOULD_NOT_NULL_WHEN_END_DEBT);
		}
		Debt savedDebt = debtDao.findOne(debt.getId());
		if(!Debt.STATUS_OPEN.equals(savedDebt.getStatus())){
			throw new GamblingException(GamblingException.DEBT_IS_CLOSED_OR_CANCELED);
		}
		savedDebt.setEndDate(new Date());
		savedDebt.setResult(debt.getResult());
		savedDebt.setStatus(Debt.STATUS_CLOSE); 
		transactionService.end(savedDebt);
		return debtDao.save(savedDebt);
	}
	
	public Debt findById(Long id){
		return debtDao.findOne(id);
	}
}

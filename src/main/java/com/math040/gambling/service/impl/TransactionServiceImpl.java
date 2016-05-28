package com.math040.gambling.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Amount;
import com.math040.gambling.dto.Debt;
import com.math040.gambling.dto.Transaction;
import com.math040.gambling.repository.DebtRepository;
import com.math040.gambling.repository.TransactionRepository;
import com.math040.gambling.service.TransactionService; 

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionRepository transDao;
	
	@Autowired
	DebtRepository debtDao;
	 
	public Transaction create(Transaction transaction) throws GamblingException{
		Assert.notNull(transaction);
		if(transaction.getGambler()==null || transaction.getGambler().getId()==null){
			throw new GamblingException(GamblingException.TRANS_USER_ID_SHOULD_NOT_NULL);
		}
		if(transaction.getDebt()==null || transaction.getDebt().getId()==null){
			throw new GamblingException(GamblingException.TRANS_DEBT_ID_SHOULD_NOT_NULL);
		} 
		Debt debt = debtDao.findOne(transaction.getDebt().getId());
		if(debt==null){
			throw new GamblingException(GamblingException.TRANS_DEBT_ID_SHOULD_NOT_NULL);
		}
		if(debt.getDealer().equals(transaction.getGambler())){
			throw new GamblingException(GamblingException.TRANS_DEALER_SHOULD_NOT_GAMBLE);
		}
		
		
		
		transaction.setDebt(debt);
		transaction.setIsDealer(Transaction.NOT_DEALER);
		transaction.setCreateDate(new Date());
		
		if(debt.getDeadline().before(transaction.getCreateDate())){
			throw new GamblingException(GamblingException.TRANS_SHOULD_NOT_GAMBLE_AFTER_DEADLINE);
		}
		
		if(!validateOneShouldDebtOnceInOneDebt(transaction)){
			throw new GamblingException(GamblingException.TRANS_GAMBLER_SHOULD_GAMBLE_ONCE_IN_ONE_GAME);
		}
		if(!transaction.validatePredict()){
			throw new GamblingException(GamblingException.TRANS_PREDICT_NOT_CORRECT);
		}
		if(!checkAmountAvailable(transaction)){
			throw new GamblingException(GamblingException.TRANS_AMOUNT_NOT_CORRECT);
		}
		return transDao.save(transaction);
		
	}
	
	private boolean checkAmountAvailable(Transaction transaction) throws GamblingException {
		Assert.notNull(transaction);
		Assert.notNull(transaction.getDebt());
		Assert.notNull(transaction.getDebt().getId()); 
		if(!transaction.validatePredict()){
			throw new GamblingException(GamblingException.TRANS_PREDICT_NOT_CORRECT);
		}
		List<Transaction> predictedTrans = transDao.findByDebt_IdAndPredict(transaction.getDebt().getId(), transaction.getPredict());
		List<Integer> predictedAmounts = new ArrayList<>();
		if(!CollectionUtils.isEmpty(predictedTrans)){
			for(Transaction trans:predictedTrans){
				predictedAmounts.add(trans.getAmount());
			}
		} 
		return Amount.validate(predictedAmounts, transaction.getAmount());
	}
	 
	private boolean validateOneShouldDebtOnceInOneDebt(Transaction transaction){
		List<Transaction> trans = transDao.findByDebt_idAndGambler_id(transaction.getDebt().getId(), transaction.getGambler().getId());
		if(CollectionUtils.isEmpty(trans)){
			return true;
		}
		return false;
	}

	@Override
	public void end(Debt debt) throws GamblingException {
		 Assert.notNull(debt);
		 Assert.notNull(debt.getId());  
		 Integer count = transDao.countByDebt(debt);
		 if(count == 0){
			 return;
		 }
		 if(Debt.RESULT_YES.equals(debt.getResult())){
			 transDao.setWinAmountWhenWin(debt, Debt.RESULT_YES);
			 transDao.setWinAmountWhenLose(debt, Debt.RESULT_NO); 
		 } 
		 if(Debt.RESULT_NO.equals(debt.getResult())){
			 transDao.setWinAmountWhenWin(debt, Debt.RESULT_NO);
			 transDao.setWinAmountWhenLose(debt, Debt.RESULT_YES); 
		 } 
		 if(Debt.RESULT_DEALER_LOSE.equals(debt.getResult())){
			 transDao.setWinAmountWhenWin(debt, Debt.RESULT_NO);
			 transDao.setWinAmountWhenWin(debt, Debt.RESULT_YES); 
		 } 
		 createDealerTrans(debt);
	}
	
	private void createDealerTrans(Debt debt){
		Integer gamblerWinAmount = transDao.sumWinSumAmountByDebt(debt);
		Transaction trans = new Transaction();
		trans.setAmount(-1*gamblerWinAmount); 
		trans.setWinAmount(-1*gamblerWinAmount); 
		trans.setIsDealer(Transaction.IS_DEALER);
		trans.setCreateDate(new Date());
		trans.setDebt(debt);
		trans.setGambler(debt.getDealer());  
		transDao.save(trans);
	}

	@Override
	public List<Transaction> findByDebt(Debt debt) { 
		 Assert.notNull(debt);
		 Assert.notNull(debt.getId());  
		return transDao.findByDebtOrderByPredictDesc(debt);
	}

	@Override
	public Map<String, List<Integer>> getAvailablePredictAmounts(Debt debt) { 
		 Assert.notNull(debt);
		 Assert.notNull(debt.getId());  
		List<Integer> yesAmmounts = Amount.getAvailableAmountByTransList(
				transDao.findByDebt_IdAndPredict(debt.getId(), Transaction.PREDICT_YES));
		List<Integer> noAmmounts = Amount.getAvailableAmountByTransList(
				transDao.findByDebt_IdAndPredict(debt.getId(), Transaction.PREDICT_NO));
		Map<String,List<Integer>> result = new HashMap<>();
		result.put(Transaction.PREDICT_YES, yesAmmounts);
		result.put(Transaction.PREDICT_NO, noAmmounts);
		return result;
	}
}

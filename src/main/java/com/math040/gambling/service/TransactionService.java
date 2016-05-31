package com.math040.gambling.service;

import java.util.List;
import java.util.Map;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Debt;
import com.math040.gambling.dto.Transaction;

public interface TransactionService {  
 Transaction create(Transaction transaction) throws GamblingException; 
  void end(Debt debt)throws GamblingException; 
  List<Transaction> findByDebt(Debt debt);
  Map<String,List<Integer>> getAvailablePredictAmounts(Debt debt);
  List<Transaction> findClosedTransBySeason() throws GamblingException;
}

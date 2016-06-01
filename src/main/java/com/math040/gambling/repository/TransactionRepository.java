package com.math040.gambling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.vo.Debt;
import com.math040.gambling.vo.Transaction;
import com.math040.gambling.vo.User; 

@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> { 
	List<Transaction> findByDebt_IdAndPredict(Long debtId,String predict);
	List<Transaction> findByDebt_idAndGambler_id(Long debtId,Long userId);
	List<Transaction> findByDebtOrderByPredictDesc(Debt debt);
	List<Transaction> findByDebtOrderByAmountDesc(Debt debt);
	List<Transaction> findByDebt_seasonAndDebt_statusOrderByGambler_idAsc(int season,String status);
	
	@Modifying(clearAutomatically=true)
	@Query("update Transaction trans set trans.winAmount=trans.amount where trans.debt=:debt and trans.predict=:predict")
	void setWinAmountWhenWin(@Param("debt") Debt debt, @Param("predict") String predict);
	
	@Modifying(clearAutomatically=true)
	@Query("update Transaction trans set trans.winAmount=-1*trans.amount where trans.debt=:debt and trans.predict=:predict")
	void setWinAmountWhenLose(@Param("debt") Debt debt, @Param("predict") String predict);
	
	@Query("select sum(trans.winAmount) from Transaction trans where trans.debt=:debt  ")
	Integer sumWinSumAmountByDebt(@Param("debt") Debt debt); 
	
	@Query("select count(trans.id) from Transaction trans where trans.debt=:debt")
	Integer countByDebt(@Param("debt") Debt debt);
	
	@Query("select count(trans.id) from Transaction trans where trans.gambler=:gambler and trans.debt.season=:season "
	+ "   and trans.winAmount>0  and trans.debt.status='"+Debt.STATUS_CLOSE+"'  and trans.isDealer ='"+Transaction.NOT_DEALER+"'  ")
	Integer countWinTransBySeasonAndGamblerAndNotDealer(@Param("season") int season, @Param("gambler") User gambler);
	
	@Query("select count(trans.id) from Transaction trans where trans.gambler=:gambler and trans.debt.season=:season "
	+ "   and trans.debt.status='"+Debt.STATUS_CLOSE+"'  and trans.isDealer ='"+Transaction.NOT_DEALER+"'  ")
	Integer countTransBySeasonAndGamblerAndNotDealer(@Param("season") int season, @Param("gambler") User gambler);
}

package com.math040.gambling.dto;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TR_TRANSACTION") 
public class Transaction extends BaseDto{
	public final static String PREDICT_YES="Y";
	public final static String PREDICT_NO="N";
	public final static String IS_DEALER="Y";
	public final static String NOT_DEALER="N";
	
	@ManyToOne
	@JoinColumn(name="debt_id", nullable=false, updatable=false)
	private Debt debt;
		
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false, updatable=false) 
	private User gambler;
	
	@Column(name="predict" ,length=1)
	private String predict;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="amount")
	private int amount;
 
	@Column(name="win_amount")
	private int winAmount;
	
	@Column(name="is_dealer",length=1)
	private String isDealer;
	
	@Transient
	private String sideAmmount;
	
	public Debt getDebt() {
		return debt;
	}

	public void setDebt(Debt debt) {
		this.debt = debt;
	}
	 
	public User getGambler() {
		return gambler;
	}

	public void setGambler(User gambler) {
		this.gambler = gambler;
	}

	public String getPredict() {
		return predict;
	}

	public void setPredict(String predict) {
		this.predict = predict;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public boolean validatePredict(){
		if(PREDICT_NO.equals(predict) || PREDICT_YES.equals(predict)){
			return true;
		}
		return false;
	}

	public int getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(int winAmount) {
		this.winAmount = winAmount;
	}

	public String getIsDealer() {
		return isDealer;
	}

	public void setIsDealer(String isDealer) {
		this.isDealer = isDealer;
	}

	public String getSideAmmount() {
		return sideAmmount;
	}

	public void setSideAmmount(String sideAmmount) {
		this.sideAmmount = sideAmmount;
	}
	 
	public boolean validateSideAmount(){
		 Pattern pattern = Pattern.compile("^["+PREDICT_YES+PREDICT_NO+"]{1}\\d{1,2}$");
		 Matcher matcher = pattern.matcher(sideAmmount);
		 return matcher.matches();
	}
}

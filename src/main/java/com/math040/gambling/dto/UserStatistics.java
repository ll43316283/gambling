package com.math040.gambling.dto;
 
 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TR_USER_STATISTICS") 
public class UserStatistics extends BaseDto{  
	 
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false, updatable=false) 
	private User gambler;
	
	private int amount;
	
	@Column(name="winning_rate",precision=3,scale=2)
	private Double winningRate;
	
	private int season;

	@Column(name="update_date")
	private Date updateDate;
	
	public User getGambler() {
		return gambler;
	}

	public void setGambler(User gambler) {
		this.gambler = gambler;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Double getWinningRate() {
		return winningRate;
	}

	public void setWinningRate(Double winningRate) {
		this.winningRate = winningRate;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
  
	
}

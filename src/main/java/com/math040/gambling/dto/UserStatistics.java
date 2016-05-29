package com.math040.gambling.dto;
 
 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TR_USER_STATISTICS") 
public class UserStatistics extends BaseDto{  
	 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id", nullable=false, updatable=false) 
	private User gambler;
	
	private int amount;
	
	@Column(name="winning_rate",precision=3,scale=2)
	private Double winningRate;
	
	private int season;

	@Column(name="update_date")
	private Date updateDate;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="TR_USERSTAT_TITLE_MAP", joinColumns=@JoinColumn(name="us_id"),
	inverseJoinColumns=@JoinColumn(name="title_id"))
	List<Title> titles = new ArrayList<>();
	
	@Column(name="ranking")
	private Integer ranking;
	
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
		if(winningRate==null){
			this.winningRate = winningRate;
		}else{
			BigDecimal   b   =   new   BigDecimal(winningRate);  
			this.winningRate   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();   
		}
		 
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

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	} 
	
}

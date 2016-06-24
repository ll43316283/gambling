package com.math040.gambling.dto;

import com.math040.gambling.vo.UserStatistics;

public class Rank {
	private String name;
	private Double rate;
	private Integer score;
	private Integer rank;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public Rank(){
		
	}
	
	public Rank(UserStatistics us){ 
		this.setName(us.getGambler().getUserName());
		this.setScore(us.getAmount());
		this.setRate(us.getWinningRate()*100);
		this.setRank(us.getRanking());
	}
}

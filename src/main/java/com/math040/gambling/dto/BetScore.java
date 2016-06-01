package com.math040.gambling.dto;

public class BetScore {
	private Long betId;
	private String title;
	private Integer amount;
	 
	
	public BetScore(Long betId, String title, Integer amount) {
		super();
		this.betId = betId;
		this.title = title;
		this.amount = amount;
	}
	
	public Long getBetId() {
		return betId;
	}
	public void setBetId(Long betId) {
		this.betId = betId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	
}

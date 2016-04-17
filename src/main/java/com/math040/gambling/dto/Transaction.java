package com.math040.gambling.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TR_TRANSACTION") 
public class Transaction {
	public final static String PREDICT_YES="Y";
	public final static String PREDICT_NO="N";
	
	@SequenceGenerator(name = "SEQ_TRANSCATION", sequenceName = "SEQ_TRANSCATION")
    @Id
    @GeneratedValue(generator="SEQ_TRANSCATION")
	private Long id;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	 
}

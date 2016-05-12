package com.math040.gambling.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.math040.gambling.util.DateUtil;

@Entity
@Table(name = "TR_DEBT") 
public class Debt extends BaseDto{ 
	public final static String RESULT_YES="Y";
	public final static String RESULT_NO="N";
	public final static String RESULT_DEALER_LOSE="D";
	
	public final static String STATUS_OPEN="O";
	public final static String STATUS_CLOSE="C";
	public final static String STATUS_CANCEL="E"; 
	 
	
	@Column(name="title", length=100)
	private String title;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false, updatable=false) 
	private User dealer;
	
	@Column(name="deadline")
	private Date deadline;
	
	@Column(name="result",length=1)
    private String result;
    
	@Column(name="end_Date")
    private Date endDate;
    
	@Column(name="status",length=1)
    private String status;
    
	@Column(name="create_Date")
    private Date createDate;
	 
	@Column
	private int season;
      
	@Transient
	private String deadLineStr;
	 
	
	public String getDeadLineStr() {
		return deadLineStr;
	}

	public void setDeadLineStr(String deadLineStr) {
		this.deadLineStr = deadLineStr;
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getDealer() {
		return dealer;
	}

	public void setDealer(User dealer) {
		this.dealer = dealer;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}
  
	public void convertStrToDate(){
		if(StringUtils.hasText(deadLineStr)){
			try {
				this.deadline=DateUtil.parse(deadLineStr);
			} catch (Exception e) { 
			}
		}
	}
}

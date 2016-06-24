package com.math040.gambling.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Table;

@Entity
@Table(name = "TR_SEASON") 
public class Season extends BaseDto{     
    public final static String ACTIVE_Y="Y";
    public final static String ACTIVE_N="N";
    
	private int season;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate; 

	@Column(name="active", length=1)
	private String active;
	
	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	} 
}

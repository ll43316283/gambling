package com.math040.gambling.dto;
 
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.Table;

@Entity
@Table(name = "TR_TITLE") 
public class Title extends BaseDto{   
	public final static String TITLE_CODE_RICHEST_PERSON = "RP";
	public final static String TITLE_CODE_HIGHEST_RATE = "HR";
	
	@Column(name="title", length=100)
	private String title;
	
	
	@Column(name="description", length=1000)
	private String description;
 
	private String code;
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	 
	
}

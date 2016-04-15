package com.math040.gambling.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TR_DEBT") 
public class DebtDto { 
	@SequenceGenerator(name = "SEQ_DEBT", sequenceName = "SEQ_DEBT")
    @Id
    @GeneratedValue(generator="SEQ_DEBT")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false, updatable=false) 
	private UserDto user;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	 
	
}

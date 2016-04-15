package com.math040.gambling.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TR_USER") 
public class UserDto {   
	public final static String ROLE_USER="ROLE_USER";
	public final static String ROLE_ADMIN="ROLE_ADMIN";
	
	@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER")
    @Id
    @GeneratedValue(generator="SEQ_USER")
	private Long id;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	 
	@Column
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	} 
	
}

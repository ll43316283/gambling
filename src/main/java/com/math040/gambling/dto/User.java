package com.math040.gambling.dto;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Table;

@Entity
@Table(name = "TR_USER") 
public class User extends BaseDto{   
	public final static String ROLE_USER="ROLE_USER";
	public final static String ROLE_ADMIN="ROLE_ADMIN"; 
	
	@Column(length=20)
	private String userName;
	
	@Column(length=50)
	private String password;
	 
	@Column(length=20)
	private String role;
 
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

package com.stackroute.accountmanager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	private String userId;
	
	private String name;
	
	private String email;
	
	private String password;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	public User(String userId, String name, String email, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

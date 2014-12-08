package com.sharegame.model.user;

import com.sharegame.model.portfolio.Portfolio;

public class User {

	private String username;
	private String password;
	private String firstname;
	private String surname;
	private String email;
	private Gender gender;
	private long userID;
	private Portfolio portfolio;
	
	public User(){
		
	}
	
	public Portfolio getPortfolio(){
		return portfolio;
	}
	
	public void setPortfolio(Portfolio portfolio){
		this.portfolio = portfolio;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
	
}

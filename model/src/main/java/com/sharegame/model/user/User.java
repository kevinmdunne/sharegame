package com.sharegame.model.user;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sharegame.model.portfolio.Portfolio;

@Entity
@Table(name = "USER")
public class User implements Serializable{

	private static final long serialVersionUID = -1662596162549941421L;

	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private Gender gender;
	
	@Id @GeneratedValue
	@Column(name = "id")
	private long userID;
	
	@ManyToOne(cascade = CascadeType.ALL)
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

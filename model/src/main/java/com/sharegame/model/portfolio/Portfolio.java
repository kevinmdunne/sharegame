package com.sharegame.model.portfolio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PORTFOLIO")
public class Portfolio implements Serializable{

	private static final long serialVersionUID = 6008509036772983050L;

	@Column(name = "cashBalance")
	private int cashBalance;

	@Id @GeneratedValue
	@Column(name = "id")
	private long id;

	public Portfolio() {

	}

	public int getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(int cashBalance) {
		this.cashBalance = cashBalance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

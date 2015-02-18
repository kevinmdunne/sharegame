package com.sharegame.model.portfolio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PORTFOLIO")
public class Portfolio implements Serializable{

	private static final long serialVersionUID = 5884527187625392803L;

	@Column(name = "cashBalance")
	private int cashBalance;

	@Id @GeneratedValue
	@Column(name = "id")
	private long id;
	
	@OneToMany (fetch = FetchType.EAGER, cascade=CascadeType.ALL )
	private List<Holding> holdings;

	public Portfolio() {
		holdings = new ArrayList<Holding>();
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
	
	public List<Holding> getHoldings(){
		return this.holdings;
	}
	
	public void setHoldings(List<Holding> holdings){
		this.holdings = holdings;
	}
	
	public void addHolding(Holding holding){
		this.holdings.add(holding);
	}

}

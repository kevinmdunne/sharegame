package com.sharegame.model.portfolio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sharegame.model.stock.Stock;

@Entity
@Table(name = "HOLDING")
public class Holding implements Serializable{

	private static final long serialVersionUID = 5220795152163465111L;

	@OneToOne(cascade = CascadeType.ALL)
	private Stock stock;
	
	@Column(name = "amount")
	private int amount;
	
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;
	
	public Holding(){
		
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

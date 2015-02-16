package com.sharegame.model.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock implements Serializable{

	private static final long serialVersionUID = -8704995635093647168L;

	@Column(name = "stockName")
	private String stockName;
	
	@Id
	@Column(name = "symbol")
	private String symbol;
	
	@Column(name = "price")
	private int price;
	
	public Stock(){
		
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}

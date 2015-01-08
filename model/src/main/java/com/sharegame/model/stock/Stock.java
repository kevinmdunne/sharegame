package com.sharegame.model.stock;

import java.io.Serializable;

public class Stock implements Serializable{

	private static final long serialVersionUID = -8704995635093647168L;
	
	private String stockName;
	private String symbol;
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

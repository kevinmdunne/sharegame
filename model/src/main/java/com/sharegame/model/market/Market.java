package com.sharegame.model.market;

import java.util.Set;

import com.sharegame.model.stock.Stock;

public class Market {

    private String name;
    private String symbol;
    private Set<Stock> stocks;
    private long id;
    
    public Market(){
    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Set<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
    

}

package com.sharegame.model.market;

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

import com.sharegame.model.stock.Stock;

@Entity
@Table(name = "MARKET")
public class Market implements Serializable{

	private static final long serialVersionUID = 8767644430840459593L;

	@Column(name = "name")
    private String name;
	
	@Column(name = "symbol")
    private String symbol;
	
	@OneToMany (fetch = FetchType.EAGER, cascade=CascadeType.ALL )
    private List<Stock> stocks;
    
    @Id @GeneratedValue
    @Column(name = "id")
    private long id;
    
    public Market(){
    	stocks = new ArrayList<Stock>();
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

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

package com.sharegame.model.order;

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
@Table(name = "ORDER")
public class Order implements Serializable{
	
	private static final long serialVersionUID = -8992689848027042423L;

	@Column(name = "type")
	private OrderType type;
	
	@Column(name = "username")
	private String username;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Stock stock;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "status")
	private OrderStatus status;
	
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;

	public Order(){
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}
}

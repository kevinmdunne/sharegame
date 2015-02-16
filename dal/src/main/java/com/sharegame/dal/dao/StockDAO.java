package com.sharegame.dal.dao;

import com.sharegame.model.stock.Stock;

public class StockDAO extends AbstractDAO<Stock>{

	@Override
	public Class<Stock> getModelClass() {
		return Stock.class;
	}
	
}

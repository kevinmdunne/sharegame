package com.sharegame.pricing.provider;

import java.util.List;

import com.sharegame.model.stock.Stock;


public interface PriceProvider {

	public String getMarketSymbol();
	public String getMarketName();
	public List<Stock> getStockPrices();
}

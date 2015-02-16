package com.sharegame.pricing.engine;

import java.util.List;

import com.sharegame.dal.dao.DAOFactory;
import com.sharegame.dal.dao.DataAccessObject;
import com.sharegame.model.market.Market;
import com.sharegame.model.stock.Stock;
import com.sharegame.pricing.provider.PriceProvider;
import com.sharegame.pricing.provider.ProvidersRegistry;

public class UpdatePricesJob implements Runnable {
	
	public UpdatePricesJob(){

	}
	
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				ProvidersRegistry registry = ProvidersRegistry.getInstance();
				List<Class<? extends PriceProvider>> providers = registry.getProivders();

				for (Class<? extends PriceProvider> clazz : providers) {
					PriceProvider provider = clazz.newInstance();
					Market market = this.getMarket(provider);
					List<Stock> stocks = provider.getStockPrices();
					this.mergeStocks(stocks,market);
					DataAccessObject<Market> dao = (DataAccessObject<Market>) DAOFactory.getInstance().getDAO(market);
					dao.save(market);
				}
				Thread.sleep(1800000);
			}
		} catch (InterruptedException e) {
			// good practice
			Thread.currentThread().interrupt();
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mergeStocks(List<Stock> stocks,Market market){
		List<Stock> marketStocks = market.getStocks();
		boolean found = false;
		for(Stock stock : stocks){
			for(Stock marketStock : marketStocks){
				if(marketStock.getSymbol().equals(stock.getSymbol())){
					marketStock.setPrice(stock.getPrice());
					found = true;
					break;
				}
			}
			if(!found){
				market.getStocks().add(stock);
			}
			found = false;
		}
	}

	private Market getMarket(PriceProvider provider) {
		String marketSymbol = provider.getMarketSymbol();
		String marketName = provider.getMarketName();
		Market prototype = new Market();
		prototype.setSymbol(marketSymbol);
		DataAccessObject<Market> dao = (DataAccessObject<Market>) DAOFactory.getInstance().getDAO(prototype);
		List<Market> result = dao.find(prototype);
		
		if(result.isEmpty()){
			prototype.setName(marketName);
			return prototype;
		}else{
			return (Market)result.get(0);
		}
	}
}

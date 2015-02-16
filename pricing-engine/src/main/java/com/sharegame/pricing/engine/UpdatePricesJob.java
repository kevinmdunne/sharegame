package com.sharegame.pricing.engine;

import java.util.List;

import com.sharegame.dal.dao.DAOFactory;
import com.sharegame.dal.dao.DataAccessObject;
import com.sharegame.model.market.Market;
import com.sharegame.model.stock.Stock;
import com.sharegame.pricing.provider.PriceProvider;
import com.sharegame.pricing.provider.ProvidersRegistry;


public class UpdatePricesJob implements Runnable {

	//private DALService service;
	
	public UpdatePricesJob(){
		//service = new DALService();
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				ProvidersRegistry registry = ProvidersRegistry.getInstance();
				List<Class<? extends PriceProvider>> providers = registry.getProivders();

				for (Class<? extends PriceProvider> clazz : providers) {
					System.out.println("Instantiating provider");
					PriceProvider provider = clazz.newInstance();
					System.out.println("Fetching market");
					Market market = this.getMarket(provider);
					System.out.println("Getting stocks");
					List<Stock> stocks = provider.getStockPrices();
					System.out.println("Merging stocks");
//					List<Stock> merged = this.mergeStocks(stocks);
					market.getStocks().clear();
					market.setStocks(stocks);
					System.out.println("Saving objects");
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
	
//	private Set<Stock> mergeStocks(List<Stock> stocks){
//		List<Stock> results = new ArrayList<Stock>();
//		Set<Stock> results = new 
//		for(Stock stock : stocks){
//			List<Object> fetchResults = service.fetch(stock);
//			if(fetchResults.isEmpty()){
//				results.add(stock);
//			}else{
//				results.add((Stock)fetchResults.get(0));
//			}
//		}
//		return results;
//	}

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

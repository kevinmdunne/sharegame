package com.sharegame.dal.sql;

import com.sharegame.model.market.Market;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.stock.Stock;
import com.sharegame.model.user.User;

public class SQLGeneratorFactory {
	
	private static SQLGeneratorFactory INSTANCE;

	private SQLGeneratorFactory() {

	}

	public static SQLGeneratorFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SQLGeneratorFactory();
		}

		return INSTANCE;
	}

	public SQLGenerator createGenerator(Object object) {
		if (object instanceof User) {
			return new UserSQLGenerator();
		} else if (object instanceof Portfolio) {
			return new PortfolioSQLGenerator();
		} else if(object instanceof Market){
			return new MarketSQLGenerator();
		} else if(object instanceof Stock){
			return new StockSQLGenerator();
		}

		return null;

	}

}

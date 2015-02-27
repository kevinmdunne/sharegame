package com.sharegame.dal.dao;

import com.sharegame.model.market.Market;
import com.sharegame.model.portfolio.Holding;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.stock.Stock;
import com.sharegame.model.user.User;

public class DAOFactory {

	private static DAOFactory INSTANCE;

	private DAOFactory() {

	}

	public static DAOFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DAOFactory();
		}

		return INSTANCE;
	}

	public DataAccessObject<?> getDAO(Object object) {
		if (object instanceof User) {
			return new UserDAO();
		} else if (object instanceof Portfolio) {
			return new PortfolioDAO();
		}  else if(object instanceof Market){
			return new MarketDAO();
		} else if(object instanceof Stock){
			return new StockDAO();
		} else if(object instanceof Holding){
			return new HoldingDAO();
		}
		return null;

	}

}

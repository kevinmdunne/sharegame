package com.sharegame.dal.dao;

import com.sharegame.model.portfolio.Portfolio;
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
		} 
		return null;

	}

}

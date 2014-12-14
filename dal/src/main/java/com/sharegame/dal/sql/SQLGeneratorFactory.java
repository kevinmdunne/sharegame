package com.sharegame.dal.sql;

import com.sharegame.model.portfolio.Portfolio;
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
		}

		return null;

	}

}

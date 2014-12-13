package com.sharegame.dal.dao;

import com.sharegame.model.portfolio.Portfolio;

public class PortfolioDAO extends AbstractDAO<Portfolio>{

	@Override
	public Class<Portfolio> getModelClass() {
		return Portfolio.class;
	}
}

package com.sharegame.dal.dao;

import com.sharegame.model.portfolio.Holding;

public class HoldingDAO extends AbstractDAO<Holding>{

	@Override
	public Class<Holding> getModelClass() {
		return Holding.class;
	}
}

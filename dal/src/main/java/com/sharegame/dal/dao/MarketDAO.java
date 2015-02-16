package com.sharegame.dal.dao;

import com.sharegame.model.market.Market;

public class MarketDAO extends AbstractDAO<Market>{

	@Override
	public Class<Market> getModelClass() {
		return Market.class;
	}
}

package com.sharegame.dal.sql;

import java.util.ArrayList;
import java.util.List;

import com.sharegame.model.market.Market;

public class MarketSQLGenerator implements SQLGenerator{

	@Override
	public String getSQL(Object object) {
		Market market = (Market) object;
		List<String> criteria = new ArrayList<String>();

		String hql = "FROM Market m";

		if (market.getName() != null && !market.getName().isEmpty()) {
			criteria.add(" m.name=" + "'" + market.getName() + "'");
		}

		if (market.getSymbol() != null && !market.getSymbol().isEmpty()) {
			criteria.add(" m.symbol=" + "'" + market.getSymbol() + "'");
		}

		if (!criteria.isEmpty()) {
			hql = hql + " WHERE";
			hql = hql + criteria.get(0);
			for (int i = 1; i < criteria.size(); i++) {
				hql = hql + " AND" + criteria.get(i);
			}
		}

		return hql;
	}

}

package com.sharegame.dal.sql;

import java.util.ArrayList;
import java.util.List;

import com.sharegame.model.stock.Stock;

public class StockSQLGenerator implements SQLGenerator {

	@Override
	public String getSQL(Object object) {
		Stock stock = (Stock) object;
		List<String> criteria = new ArrayList<String>();

		String hql = "FROM Stock s";

		if (stock.getStockName() != null && !stock.getStockName().isEmpty()) {
			criteria.add(" s.stockName=" + "'" + stock.getStockName() + "'");
		}

		if (stock.getSymbol() != null && !stock.getSymbol().isEmpty()) {
			criteria.add(" s.symbol=" + "'" + stock.getSymbol() + "'");
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

package com.sharegame.dal.sql;

import java.util.ArrayList;
import java.util.List;

import com.sharegame.model.portfolio.Holding;

public class HoldingSQLGenerator implements SQLGenerator{

	@Override
	public String getSQL(Object object) {
		Holding holding = (Holding) object;
		List<String> criteria = new ArrayList<String>();

		String hql = "FROM Holding h";

		if (holding.getAmount() >= 0) {
			criteria.add(" h.amount=" + holding.getAmount());
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

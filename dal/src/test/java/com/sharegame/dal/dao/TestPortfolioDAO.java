package com.sharegame.dal.dao;

import org.junit.Test;

import com.sharegame.model.portfolio.Portfolio;

public class TestPortfolioDAO {

	@Test
	public void testCreateUser(){		
		Portfolio portfolio = new Portfolio();
		portfolio.setCashBalance(888);
		
		PortfolioDAO dao = new PortfolioDAO();
		dao.save(portfolio);
	}
}

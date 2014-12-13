package com.sharegame.dal.dao;

import org.junit.Assert;
import org.junit.Test;

import com.sharegame.dal.dao.DAOFactory;
import com.sharegame.dal.dao.DataAccessObject;
import com.sharegame.dal.dao.PortfolioDAO;
import com.sharegame.dal.dao.UserDAO;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.user.User;

public class TestDAOFactory {

	@Test
	public void testGetDAO() {
		User user = new User();
		Portfolio portfolio = new Portfolio();

		DAOFactory factory = DAOFactory.getInstance();
		Assert.assertNotNull(factory);

		DataAccessObject<?> dao = factory.getDAO(user);
		Assert.assertTrue(dao instanceof UserDAO);

		dao = factory.getDAO(portfolio);
		Assert.assertTrue(dao instanceof PortfolioDAO);
	}

}

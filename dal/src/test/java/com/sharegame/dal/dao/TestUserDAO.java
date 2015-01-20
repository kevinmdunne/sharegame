package com.sharegame.dal.dao;

import org.junit.Test;

import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.user.Gender;
import com.sharegame.model.user.User;

public class TestUserDAO {

	@Test
	public void testCreateUser(){
		User user = new User();
		user.setFirstname("kevin");
		user.setGender(Gender.MALE);
		user.setPassword("password");
		user.setSurname("dunne");
		user.setUsername("kevinmdunne@gmail.com");
		
		Portfolio portfolio = new Portfolio();
		portfolio.setCashBalance(888);
		
		user.setPortfolio(portfolio);
		
		UserDAO dao = new UserDAO();
		dao.save(user);
	}
}

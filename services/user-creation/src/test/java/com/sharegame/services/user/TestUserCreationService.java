package com.sharegame.services.user;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.mini.data.MicroserviceRequest;
import com.mini.exception.ServiceExecutionException;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.user.Gender;
import com.sharegame.model.user.User;
import com.sharegame.services.user.UserCreationService;

public class TestUserCreationService extends TestCase {

	public TestUserCreationService(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TestUserCreationService.class);
	}

	public void testCreatingNewUser() {
		UserCreationService service = new UserCreationService(null);
		User user = new User();
		user.setFirstname("kevin");
		user.setEmail("kevinmdunne@gmail.com");
		user.setGender(Gender.MALE);
		user.setPassword("password");
		user.setSurname("dunne");
		user.setUsername("kevinmdunne");
		
		Portfolio portfolio = new Portfolio();
		portfolio.setCashBalance(888);
		
		user.setPortfolio(portfolio);
		
		MicroserviceRequest request = new MicroserviceRequest(service.getID());
		request.setPayload(user);
		try{
			service.execute(request);
		}catch(ServiceExecutionException e){
			e.printStackTrace();
			Assert.fail();
		}
	}
}

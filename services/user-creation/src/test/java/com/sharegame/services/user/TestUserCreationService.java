package com.sharegame.services.user;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.mini.data.MicroserviceRequest;
import com.mini.exception.ServiceExecutionException;
import com.mini.io.adapter.DummyQueueAdapter;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.user.Gender;
import com.sharegame.model.user.User;

public class TestUserCreationService extends TestCase {

	public TestUserCreationService(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TestUserCreationService.class);
	}

	public void testCreatingNewUser() {
		DummyQueueAdapter qAdapter = new DummyQueueAdapter();
		UserCreationService service = new UserCreationService(qAdapter);
		User user = new User();
		user.setFirstname("kevin");
		user.setGender(Gender.MALE);
		user.setPassword("password");
		user.setSurname("dunne");
		user.setUsername("kevinmdunne@gmail.com");
		
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

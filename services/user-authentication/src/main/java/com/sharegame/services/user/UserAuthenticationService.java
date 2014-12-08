package com.sharegame.services.user;

import com.mini.exception.InfastructureException;
import com.mini.exception.ServiceExecutionException;
import com.mini.microservice.AbstractMicroservice;

public class UserAuthenticationService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.user.UserAuthenticationService";
	
	@Override
	public void execute() throws ServiceExecutionException {

	}

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public void start() throws InfastructureException {

	}

	@Override
	public void stop() throws InfastructureException {

	}

}

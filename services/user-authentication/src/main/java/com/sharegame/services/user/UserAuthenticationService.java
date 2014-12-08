package com.sharegame.services.user;

import com.mini.exception.InfastructureException;
import com.mini.exception.ServiceExecutionException;
import com.mini.io.adapter.IQueueAdapter;
import com.mini.microservice.AbstractMicroservice;
import com.mini.data.MicroserviceRequest;

public class UserAuthenticationService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.user.UserAuthenticationService";
	
	public UserAuthenticationService(IQueueAdapter queueAdapter){
		super(queueAdapter);
	}
	
	@Override
	public void execute(MicroserviceRequest request) throws ServiceExecutionException {

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

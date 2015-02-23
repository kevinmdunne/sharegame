package com.sharegame.services.stock;

import com.mini.data.MicroserviceRequest;
import com.mini.exception.ServiceExecutionException;
import com.mini.io.adapter.IQueueAdapter;
import com.mini.microservice.AbstractMicroservice;

public class BuyStockService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.stock.BuyStockService";

	public BuyStockService(IQueueAdapter queueAdapter){
		super(queueAdapter);
	}
	
	@Override
	public void execute(MicroserviceRequest request) throws ServiceExecutionException {

	}

	@Override
	public String getID() {
		return ID;
	}
}

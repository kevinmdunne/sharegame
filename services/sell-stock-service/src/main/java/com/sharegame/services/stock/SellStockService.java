package com.sharegame.services.stock;

import com.mini.data.MicroserviceRequest;
import com.mini.exception.ServiceExecutionException;
import com.mini.io.adapter.IQueueAdapter;
import com.mini.microservice.AbstractMicroservice;

public class SellStockService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.stock.SellStockService";

	public SellStockService(IQueueAdapter queueAdapter){
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

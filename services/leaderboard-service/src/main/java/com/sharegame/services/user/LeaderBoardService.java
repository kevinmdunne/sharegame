package com.sharegame.services.user;

import java.util.Scanner;

import com.mini.data.MicroserviceRequest;
import com.mini.data.MicroserviceResponse;
import com.mini.exception.ServiceExecutionException;
import com.mini.io.adapter.IQueueAdapter;
import com.mini.io.adapter.QueueAdapter;
import com.mini.io.adapter.QueueAdapterFactory;
import com.mini.io.exception.QueueException;
import com.mini.io.metadata.QueueMetaData;
import com.mini.microservice.AbstractMicroservice;

public class LeaderBoardService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.user.LeaderBoardService";

	public LeaderBoardService(IQueueAdapter queueAdapter){
		super(queueAdapter);
	}
	
	@Override
	public void execute(MicroserviceRequest request) throws ServiceExecutionException {
		MicroserviceResponse response = new MicroserviceResponse();
		response.setCorrelationID(request.getCorrelationID());
		try{
			Object data = request.getPayload();
			sendResponse(response);
		}catch(QueueException e){
			throw new ServiceExecutionException(e);
		}
	}

	@Override
	public String getID() {
		return ID;
	}

	public static void main(String[] args){   
		try{
			if(args.length == 3){
				String queueName = args[0];
				String adapterClassName = args[1];
				String serverURL = args[2];
				
				QueueMetaData queueData = new QueueMetaData(queueName, serverURL);
				QueueAdapterFactory factory = QueueAdapterFactory.getInstance();
				QueueAdapter queueAdapter = factory.createAdapter(adapterClassName, queueData);
				LeaderBoardService service = new LeaderBoardService(queueAdapter);
				System.out.println("Starting " + service.getID());
				service.start();
				
				new Scanner(System.in).nextLine();
				System.out.println("Stopping service");
				service.stop();
			}else{
				System.out.println("Usage : queueName adapterClassName serverURL");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

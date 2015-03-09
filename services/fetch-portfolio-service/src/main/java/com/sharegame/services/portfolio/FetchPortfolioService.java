package com.sharegame.services.portfolio;

import java.util.List;
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
import com.sharegame.dal.dao.DAOFactory;
import com.sharegame.dal.dao.DataAccessObject;
import com.sharegame.model.user.User;

public class FetchPortfolioService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.portfolio.FetchPortfolioService";

	public FetchPortfolioService(IQueueAdapter queueAdapter){
		super(queueAdapter);
	}
	
	@Override
	public void execute(MicroserviceRequest request) throws ServiceExecutionException {
		MicroserviceResponse response = new MicroserviceResponse();
		response.setCorrelationID(request.getCorrelationID());
		try{
			Object data = request.getPayload();
			if(data instanceof String){
				String username = data.toString();
				User user = new User();
				user.setUsername(username);
				DataAccessObject<User> dao = (DataAccessObject<User>) DAOFactory.getInstance().getDAO(user);
				List<User> users = dao.find(user);
				if(!users.isEmpty()){
					response.setPayload(users.get(0).getPortfolio());
					response.setStatus(MicroserviceResponse.SUCCESS);
				}else{
					response.setStatus(MicroserviceResponse.FAILURE);
					response.setStatusMessage("No such user");
				}
			}else{
				response.setStatus(MicroserviceResponse.FAILURE);
				response.setStatusMessage("Invalid arguments for portfolio fetch service");
			}
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
				FetchPortfolioService service = new FetchPortfolioService(queueAdapter);
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

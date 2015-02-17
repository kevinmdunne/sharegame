package com.sharegame.services.networth;

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
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.user.User;

public class UserNetWorthService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.networth.UserNetWorthService";
	
	public UserNetWorthService(IQueueAdapter queueAdapter){
		super(queueAdapter);
	}
	
	@Override
	public void execute(MicroserviceRequest request) throws ServiceExecutionException {
		try{
			MicroserviceResponse response = new MicroserviceResponse();
			response.setCorrelationID(request.getCorrelationID());
			
			Object data = request.getPayload();
			if(data instanceof User){
				User user = (User)data;
				
				if(user.getUsername() != null && !user.getUsername().isEmpty()){
					DataAccessObject<User> dao = (DataAccessObject<User>) DAOFactory.getInstance().getDAO(user);
					List<User> users = dao.find(user);
					if(!users.isEmpty()){
						User realUser = users.get(0);
						Portfolio portfolio = realUser.getPortfolio();
						int netWorth = this.calculateNetWorth(portfolio);
						response.setStatus(MicroserviceResponse.SUCCESS);
						response.setPayload(new Integer(netWorth));
					}else{
						response.setStatus(MicroserviceResponse.FAILURE);
						response.setStatusMessage("No user [" + user.getUsername() + "] found to calculate networth");
					}
				}
			}else{
				response.setStatus(MicroserviceResponse.FAILURE);
				response.setStatusMessage("Invalid arguments for net worth service");
			}
			sendResponse(response);
		}catch(QueueException e){
			throw new ServiceExecutionException(e);
		}
	}
	
	private int calculateNetWorth(Portfolio portfolio){
		int result = portfolio.getCashBalance();
		
		return result;
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
				UserNetWorthService service = new UserNetWorthService(queueAdapter);
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

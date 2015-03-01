package com.sharegame.services.stock;

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
import com.sharegame.model.order.Order;
import com.sharegame.model.portfolio.Holding;
import com.sharegame.model.stock.Stock;
import com.sharegame.model.user.User;

public class BuyStockService extends AbstractMicroservice{

	private static final String ID = "com.sharegame.services.stock.BuyStockService";

	public BuyStockService(IQueueAdapter queueAdapter){
		super(queueAdapter);
	}
	
	@Override
	public void execute(MicroserviceRequest request) throws ServiceExecutionException {
		MicroserviceResponse response = new MicroserviceResponse();
		response.setCorrelationID(request.getCorrelationID());
		
		Object data = request.getPayload();
		if(data instanceof Order){
			Order order = (Order)data;
			
			DataAccessObject<Stock> stockDAO = (DataAccessObject<Stock>) DAOFactory.getInstance().getDAO(order.getStock());
			List<Stock> stocks = stockDAO.find(order.getStock());
			
			if(!stocks.isEmpty()){
				Holding holding = new Holding();
				holding.setAmount(order.getAmount());
				holding.setStock(stocks.get(0));
				
				User user = new User();
				user.setUsername(order.getUsername());
				
				DataAccessObject<User> userDAO = (DataAccessObject<User>) DAOFactory.getInstance().getDAO(user);
				List<User> users = userDAO.find(user);
				
				if(!users.isEmpty()){
					User realUser = users.get(0);
					realUser.getPortfolio().getHoldings().add(holding);
					userDAO.save(realUser);
				}else{
					response.setStatus(MicroserviceResponse.FAILURE);
					response.setStatusMessage("No such user " + order.getUsername());
				}
			}else{
				response.setStatus(MicroserviceResponse.FAILURE);
				response.setStatusMessage("No such stock " + order.getStock().getSymbol());
			}
		}else{
			response.setStatus(MicroserviceResponse.FAILURE);
			response.setStatusMessage("Invalid arguments for user stock purchase service");
		}
		try{
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
				BuyStockService service = new BuyStockService(queueAdapter);
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

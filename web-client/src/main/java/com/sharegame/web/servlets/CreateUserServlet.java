package com.sharegame.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.connection.MicroServiceConnection;
import com.mini.data.MicroserviceRequest;
import com.mini.data.MicroserviceResponse;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.user.Gender;
import com.sharegame.model.user.User;
import com.sharegame.web.msconnection.MSConnectionManager;

public class CreateUserServlet extends HttpServlet{
	
	private static final String CREATE_USER_SERVICE_ID = "com.sharegame.services.user.UserCreationService";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String firstname = request.getParameter("firstname");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User();
		
		user.setFirstname(firstname);
		user.setGender(Gender.MALE);
		user.setPassword(password);
		user.setSurname(surname);
		user.setUsername(email);
		Portfolio portfolio = new Portfolio();
		portfolio.setCashBalance(100000);
		
		user.setPortfolio(portfolio);
		
		MicroserviceRequest msRequest = new MicroserviceRequest(CREATE_USER_SERVICE_ID);
		msRequest.setPayload(user);
		
		try{
			MicroServiceConnection connection = MSConnectionManager.getInstance().getConnection();
			MicroserviceResponse msResponse = connection.request(msRequest);
			
			if(msResponse.getStatus() == MicroserviceResponse.SUCCESS){
				out.println("User successfully created");
			}else{
				out.println("User creation failed.");
				out.println(msResponse.getStatusMessage());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}
}

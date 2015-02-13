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
import com.sharegame.model.user.Gender;
import com.sharegame.model.user.User;
import com.sharegame.web.msconnection.MSConnectionManager;

public class LoginServlet extends HttpServlet{
	
	private static final String CREATE_USER_SERVICE_ID = "com.sharegame.services.user.UserAuthenticationService";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User();
		
		user.setGender(Gender.MALE);
		user.setPassword(password);
		user.setUsername(email);
		
		MicroserviceRequest msRequest = new MicroserviceRequest(CREATE_USER_SERVICE_ID);
		msRequest.setPayload(user);
		
		try{
			MicroServiceConnection connection = MSConnectionManager.getInstance().getConnection();
			MicroserviceResponse msResponse = connection.request(msRequest);
			
			if(msResponse.getStatus() == MicroserviceResponse.SUCCESS){
				out.println("You have logged in");
			}else{
				out.println("Log in failed.");
				out.println(msResponse.getStatusMessage());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}
}

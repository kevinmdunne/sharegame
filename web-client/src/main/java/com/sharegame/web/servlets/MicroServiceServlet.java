package com.sharegame.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.mini.connection.MicroServiceConnection;
import com.mini.connection.ServiceExecutionException;
import com.mini.data.MicroserviceRequest;
import com.mini.data.MicroserviceResponse;
import com.mini.io.exception.QueueException;
import com.sharegame.web.msconnection.MSConnectionManager;

public class MicroServiceServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		String serviceID = request.getParameter("serviceid");
		String className = request.getParameter("className");
		String objectJSON = request.getParameter("payload");
		
		try{
			Class<?> clazz = Class.forName(className);
			Gson gson = new Gson();
			Object payload = gson.fromJson(objectJSON, clazz);
			
			MicroserviceRequest msRequest = new MicroserviceRequest(serviceID);
			msRequest.setPayload(payload);
			
			MicroServiceConnection connection = MSConnectionManager.getInstance().getConnection();
			MicroserviceResponse msResponse = connection.request(msRequest);
			
			if(msResponse.getStatus() == MicroserviceResponse.SUCCESS){
				String result = gson.toJson(msResponse.getPayload());
				JSONObject object = new JSONObject();
				object.put("result", result);
				out.println(object);
			}else{
				response.setStatus(500);
				out.println(msResponse.getStatusMessage());
			}
		}catch(ClassNotFoundException e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Class " + className + " not found");
		}catch(ServiceExecutionException e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}catch(QueueException e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}finally{
			out.flush();
		}
	}
}

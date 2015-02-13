package com.sharegame.web.servlets;

import com.mini.connection.MicroServiceConnection;
import com.mini.connection.MicroServiceConnectionFactory;

public class MSConnectionManager {

	private static MSConnectionManager INSTANCE;
	
	private MicroServiceConnection connection;
	
	public static MSConnectionManager getInstance(){
		if(INSTANCE == null){
			INSTANCE = new MSConnectionManager();
		}
		return INSTANCE;
	}
	
	private MSConnectionManager(){
		MicroServiceConnectionFactory factory = new MicroServiceConnectionFactory("failover://tcp://localhost:61616");
		connection = factory.createConnection("ShareGameConnectionManager");	
	}
	
	public MicroServiceConnection getConnection(){
		return connection;
	}
}

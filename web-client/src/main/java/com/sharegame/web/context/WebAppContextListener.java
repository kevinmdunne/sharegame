package com.sharegame.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sharegame.web.msconnection.MSConnectionManager;

public class WebAppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		MSConnectionManager.getInstance().closeConnection();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

	}

}

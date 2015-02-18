package com.sharegame.dal.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sharegame.model.market.Market;
import com.sharegame.model.portfolio.Holding;
import com.sharegame.model.portfolio.Portfolio;
import com.sharegame.model.stock.Stock;
import com.sharegame.model.user.User;

public class HibernateAccessManager {
	private static HibernateAccessManager INSTANCE;
	
	private SessionFactory sessionFactory;
	
	public static HibernateAccessManager getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HibernateAccessManager();
		}
		
		return INSTANCE;
	}
	
	private HibernateAccessManager(){
		Configuration config = new Configuration();
		config.configure();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Portfolio.class);
		config.addAnnotatedClass(Market.class);
		config.addAnnotatedClass(Stock.class);
		config.addAnnotatedClass(Holding.class);
		
		sessionFactory = config.buildSessionFactory();
	}
	
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    } 

}

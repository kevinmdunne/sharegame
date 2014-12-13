package com.sharegame.dal.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sharegame.dal.hibernate.HibernateAccessManager;

public abstract class AbstractDAO<T> implements DataAccessObject<T> {

    @Override
    public boolean save(T object){
    	SessionFactory factory = HibernateAccessManager.getInstance().getSessionFactory();
     	Session session = factory.getCurrentSession();
     	session.beginTransaction();
     	session.save(object);
    	session.getTransaction().commit();
     	return true;
    }
    
    @Override
    public boolean delete(T object){
    	SessionFactory factory = HibernateAccessManager.getInstance().getSessionFactory();
     	Session session = factory.getCurrentSession();
      	session.beginTransaction();
      	session.delete(object);
     	session.getTransaction().commit();
     	return true;
    } 

    @Override
    public boolean update(T object) {
    	SessionFactory factory = HibernateAccessManager.getInstance().getSessionFactory();
    	Session session = factory.getCurrentSession();
    	session.beginTransaction();
    	session.update(object);
    	session.getTransaction().commit();
    	return true;
    }
    
	@Override
	public List<T> find(T prototype) {
		return null;
	}


}

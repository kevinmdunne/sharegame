package com.sharegame.dal.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sharegame.dal.hibernate.HibernateAccessManager;
import com.sharegame.dal.sql.SQLGenerator;
import com.sharegame.dal.sql.SQLGeneratorFactory;

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
    
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(T prototype) {
		SQLGeneratorFactory factory = SQLGeneratorFactory.getInstance();
        SQLGenerator generator = factory.createGenerator(prototype);
        String hql = generator.getSQL(prototype);
        Session session = HibernateAccessManager.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);

        List<T> results = query.list();
        session.getTransaction().commit();

        return results; 

	}


}

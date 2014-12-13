package com.sharegame.dal.dao;

import java.util.List;

public interface DataAccessObject <T>{

	public boolean save(T object);
    public boolean update(T object);
    public List<T> find(T prototype);
    public boolean delete(T object);
    public Class<T> getModelClass();
}

package com.sav.autobase.dao.impl.db;

import com.sav.autobase.dao.impl.db.exceptions.DaoException;

public interface IAbstractModelDao<T>  {
	
	public T getById(Integer id) throws DaoException;
	
	public T insert(T entity) throws DaoException;
	
	public T update(T entity) throws DaoException;
	
	public void delete(Integer id) throws DaoException;
	
	

}

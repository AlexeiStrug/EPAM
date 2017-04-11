package com.sav.autobase.dao.impl.db;

import java.util.List;

public interface IGenericDao<T> {

	public T getById(Object id);

	public T insert(T entity);

	public T update(T entity);

	public void delete(Object id);
	
	List<T> getAll();

}

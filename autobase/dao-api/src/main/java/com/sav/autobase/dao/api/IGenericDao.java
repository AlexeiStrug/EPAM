package com.sav.autobase.dao.api;

import java.util.List;

public interface IGenericDao<T> {

	public T getById(Integer t);

	public T insert(T entity);
	
	public T update(T entity);

	public void delete(Integer t);
	
	List<T> getAll();

}

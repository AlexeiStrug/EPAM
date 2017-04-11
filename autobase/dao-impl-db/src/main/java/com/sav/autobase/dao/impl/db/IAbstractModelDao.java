package com.sav.autobase.dao.impl.db;

public interface IAbstractModelDao<T> {

	public T getById(Integer id);

	public T insert(T entity);

	public T update(T entity);

	public void delete(Integer id);

}

package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.Request;

public interface IRequestDao {
	
	Request getById(Integer id);
	
	Request insert(Request request);
	
	Request update(Request request);
	
	void delete(Integer id);
	
	List<Request> getAll();
	
	List<Request> findByCriteria();

}

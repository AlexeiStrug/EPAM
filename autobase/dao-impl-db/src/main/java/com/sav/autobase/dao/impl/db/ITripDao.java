package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.Trip;

public interface ITripDao {
	
	Trip getById(Integer id);
	
	Trip insert(Trip trip);
	
	Trip update(Trip trip);
	
	void delete(Integer id);
	
	List<Trip> getAll();
	
	List<Trip> findByCriteria();
	

}

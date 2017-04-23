package com.sav.autobase.dao.api;

import java.util.List;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;

public interface ITripDao extends IGenericDao<Trip> {

	List<Trip> findByCriteria();

	Trip joinGetById(Integer id);
	
	Trip getByUser(Users user);

	List<Trip> joinGetAll();
}

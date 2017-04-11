package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.Trip;

public interface ITripDao extends IGenericDao<Trip> {

	List<Trip> findByCriteria();

	Trip joinGetById(Integer id);

	List<Trip> joinGetAll();
}

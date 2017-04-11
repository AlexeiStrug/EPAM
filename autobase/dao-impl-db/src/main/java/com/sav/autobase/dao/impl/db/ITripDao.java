package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.Trip;

public interface ITripDao extends IAbstractModelDao<Trip> {

	List<Trip> getAll();

	List<Trip> findByCriteria();
}

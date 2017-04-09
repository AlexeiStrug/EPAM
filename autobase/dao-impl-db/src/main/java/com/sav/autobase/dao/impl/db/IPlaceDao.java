package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.dao.impl.db.exceptions.DaoException;
import com.sav.autobase.datamodel.Place;

public interface IPlaceDao extends IAbstractModelDao<Place> {

	Place getByStartPlace(Place place) throws DaoException;

	Place getByEndtPlace(Place place) throws DaoException;

	List<Place> getAll() throws DaoException;

}

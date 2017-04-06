package com.sav.autobase.dao.impl.db;

import java.util.List;

import com.sav.autobase.datamodel.Place;

public interface IPlaceDao {
	
	Place getById(Integer id);
	
	Place getByStartPlace(Place place);
	
	Place getByEndtPlace(Place place);
	
	Place insert(Place place);
	
	List<Place> getAll();
	
	void delete(Integer id);
	
}

package com.sav.autobase.dao.api;

import com.sav.autobase.datamodel.Place;

public interface IPlaceDao extends IGenericDao<Place> {

	Place getByStartPlace(Place place);

	Place getByEndtPlace(Place place);

}

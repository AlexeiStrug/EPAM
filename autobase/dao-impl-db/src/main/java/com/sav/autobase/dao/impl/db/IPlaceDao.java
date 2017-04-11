package com.sav.autobase.dao.impl.db;

import com.sav.autobase.datamodel.Place;

public interface IPlaceDao extends IAbstractModelDao<Place> {

	Place getByStartPlace(Place place);

	Place getByEndtPlace(Place place);

}

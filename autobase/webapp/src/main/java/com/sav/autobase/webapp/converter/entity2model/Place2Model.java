package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Place;
import com.sav.autobase.webapp.models.PlaceModel;

public class Place2Model implements Converter<Place, PlaceModel> {

	public PlaceModel convert(Place place) {
		return place2model(place);
	}
	
	private PlaceModel place2model(Place place) {
		PlaceModel placeModel = new PlaceModel();
		placeModel.setId(place.getId());
		placeModel.setPlaceStart(place.getPlaceStart());
		placeModel.setPlaceEnd(place.getPlaceEnd());
		placeModel.setDistance(place.getDistance());
		return placeModel;
	}
}

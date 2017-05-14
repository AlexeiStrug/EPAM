package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Place;
import com.sav.autobase.webapp.models.PlaceModel;

public class Model2Place implements Converter<PlaceModel, Place> {

	public Place convert(PlaceModel placeModel) {
		return model2place(placeModel);
	}
	
	private Place model2place(PlaceModel placeModel) {
		Place place = new Place();
		place.setId(placeModel.getId());
		place.setPlaceStart(placeModel.getPlaceStart());
		place.setPlaceEnd(placeModel.getPlaceEnd());
		place.setDistance(placeModel.getDistance());
		return place;
	}
}

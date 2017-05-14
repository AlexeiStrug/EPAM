package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.webapp.models.TripModel;

public class Model2Trip implements Converter<TripModel, Trip> {

	public Trip convert(TripModel tripModel) {
		return model2trip(tripModel);
	}

	private Trip model2trip(TripModel tripModel) {
		Trip trip = new Trip();
		trip.setId(tripModel.getId());
		trip.setRequest(new Model2Request().convert(tripModel.getRequest()));
		trip.setVehicle(new Model2Vehicle().convert(tripModel.getVehicle()));
		trip.setEndTrip(tripModel.isEndTrip());
		return trip;
	}

}

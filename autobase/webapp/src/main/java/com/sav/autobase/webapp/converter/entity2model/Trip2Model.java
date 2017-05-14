package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.webapp.models.TripModel;

public class Trip2Model implements Converter<Trip, TripModel> {

	public TripModel convert(Trip trip) {
		return trip2model(trip);
	}

	private TripModel trip2model(Trip trip) {
		TripModel tripModel = new TripModel();
		tripModel.setId(trip.getId());
		tripModel.setRequest(new Request2Model().convert(trip.getRequest()));
		tripModel.setVehicle(new Vehicle2Model().convert(trip.getVehicle()));
		tripModel.setEndTrip(trip.isEndTrip());
		return tripModel;
	}

}

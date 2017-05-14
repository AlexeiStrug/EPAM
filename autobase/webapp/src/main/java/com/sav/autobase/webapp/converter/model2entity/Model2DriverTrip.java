package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.webapp.models.DriverTripModel;

public class Model2DriverTrip implements Converter<DriverTripModel, Trip> {

	public Trip convert(DriverTripModel tripModel) {
		return model2driverTrip(tripModel);
	}

	private Trip model2driverTrip(DriverTripModel tripModel) {
		Trip trip = new Trip();
		trip.setId(tripModel.getId());
		trip.setRequest(new Model2ClientRequest().convert(tripModel.getRequest()));
		trip.setVehicle(new Model2Vehicle().convert(tripModel.getVehicle()));
		trip.setEndTrip(tripModel.isEndTrip());
		return trip;
	}

}

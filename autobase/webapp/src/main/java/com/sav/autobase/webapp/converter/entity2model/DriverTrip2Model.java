package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.webapp.models.DriverTripModel;

public class DriverTrip2Model implements Converter<Trip, DriverTripModel> {

	public DriverTripModel convert(Trip trip) {
		return driverTrip2model(trip);
	}
	
	private DriverTripModel driverTrip2model(Trip trip) {
		DriverTripModel tripModel = new DriverTripModel();
		tripModel.setId(trip.getId());
		tripModel.setRequest(new ClientRequest2Model().convert(trip.getRequest()));
		tripModel.setVehicle(new Vehicle2Model().convert(trip.getVehicle()));
		tripModel.setEndTrip(trip.isEndTrip());
		return tripModel;
	}

}

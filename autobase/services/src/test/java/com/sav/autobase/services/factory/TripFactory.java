package com.sav.autobase.services.factory;

import java.util.ArrayList;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Vehicle;

public class TripFactory implements IFactory<Trip> {

	Trip newTrip = new Trip();

	ArrayList<Trip> vehicles = new ArrayList<>();
	
	public TripFactory(Request request, Vehicle vehicle) {

		newTrip.setRequest(request);
		newTrip.setVehicle(vehicle);
		newTrip.setEndTrip(false);
	}


	@Override
	public Trip create() {
		return newTrip;
	}

}

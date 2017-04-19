package com.sav.autobase.services.factory;

import java.util.ArrayList;

import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;

public class VehicleFactory implements IFactory<Vehicle> {

	Vehicle vehicle = new Vehicle();

	ArrayList<Vehicle> vehicles = new ArrayList<>();

	public VehicleFactory(ModelVehicle model, Users user) {

		vehicle.setDriver(user);
		vehicle.setModel(model);
		vehicle.setReadyCrashCar(true);

	}

	@Override
	public Vehicle create() {
		return vehicle;
	}

}

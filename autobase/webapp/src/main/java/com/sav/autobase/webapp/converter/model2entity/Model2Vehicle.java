package com.sav.autobase.webapp.converter.model2entity;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.webapp.models.VehicleModel;

public class Model2Vehicle implements Converter<VehicleModel, Vehicle> {

	public Vehicle convert(VehicleModel vehicleModel) {
		return model2vehicle(vehicleModel);
	}
	
	private Vehicle model2vehicle(VehicleModel vehicleModel) {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(vehicleModel.getId());
		vehicle.setDriver(new Model2Driver().convert(vehicleModel.getDriver()));
		vehicle.setModel(new Model2ModelVehicle().convert(vehicleModel.getModel()));
		vehicle.setReadyCrashCar(vehicleModel.isReadyCrashCar());
		return vehicle;
	}
}

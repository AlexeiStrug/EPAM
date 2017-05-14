package com.sav.autobase.webapp.converter.entity2model;

import org.springframework.core.convert.converter.Converter;

import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.webapp.models.VehicleModel;

public class Vehicle2Model implements Converter<Vehicle, VehicleModel> {

	public VehicleModel convert(Vehicle vehicle) {
		return vehicle2model(vehicle);
	}
	
	private VehicleModel vehicle2model(Vehicle vehicle) {
		VehicleModel vehicleModel = new VehicleModel();
		vehicleModel.setId(vehicle.getId());
		vehicleModel.setDriver(new Driver2Model().convert(vehicle.getDriver()));
		vehicleModel.setModel(new ModelVehicle2Model().convert(vehicle.getModel()));
		vehicleModel.setReadyCrashCar(vehicle.isReadyCrashCar());
		return vehicleModel;

	}

}

package com.sav.autobase.webapp.models;

public class VehicleModel extends IdModel{

	private DriverUsersModel driver;
	private ModelVehicleModel model;
	private boolean readyCrashCar;

	public DriverUsersModel getDriver() {
		return driver;
	}

	public void setDriver(DriverUsersModel driver) {
		this.driver = driver;
	}

	public ModelVehicleModel getModel() {
		return model;
	}

	public void setModel(ModelVehicleModel model) {
		this.model = model;
	}

	public boolean isReadyCrashCar() {
		return readyCrashCar;
	}

	public void setReadyCrashCar(boolean readyCrashCar) {
		this.readyCrashCar = readyCrashCar;
	}

}

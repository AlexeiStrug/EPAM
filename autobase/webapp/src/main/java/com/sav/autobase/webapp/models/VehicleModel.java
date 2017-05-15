package com.sav.autobase.webapp.models;

/**
 * This class VehicleModel serves to store objects with properties vehicle
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class VehicleModel extends IdModel {

	/**
	 * driver - the user driver <br>
	 * model - the model vehicle <br>
	 * readyCrashcar - the status vehicle
	 */
	private DriverUsersModel driver;
	private ModelVehicleModel model;
	private Boolean readyCrashCar;

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

	public Boolean isReadyCrashCar() {
		return readyCrashCar;
	}

	public void setReadyCrashCar(Boolean readyCrashCar) {
		this.readyCrashCar = readyCrashCar;
	}

}

package com.sav.autobase.datamodel;

public class Vehicle extends AbstractModel{

	private Users driver;
	private ModelVehicle model;
	private Boolean readyCrashCar;

	public Users getDriver() {
		return driver;
	}

	public void setDriver(Users driver) {
		this.driver = driver;
	}

	public ModelVehicle getModel() {
		return model;
	}

	public void setModel(ModelVehicle model) {
		this.model = model;
	}

	public Boolean getReadyCrashCar() {
		return readyCrashCar;
	}

	public void setReadyCrashCar(Boolean readyCrashCar) {
		this.readyCrashCar = readyCrashCar;
	}

	@Override
	public String toString() {
		return "Vehicle [" + model + ", " + driver + ", readyCrashCar=" + readyCrashCar + "]";
	}

}

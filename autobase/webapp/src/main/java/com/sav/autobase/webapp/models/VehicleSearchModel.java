package com.sav.autobase.webapp.models;

/**
 * This class VehicleSearchModel serves to store objects with properties search
 * vehicle by criteria
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class VehicleSearchModel {

	/**
	 * brand - the name brand vehicle <br>
	 * type - the name type vehicle <br>
	 * nameModel - the name model vehicle <br>
	 * countOfPassenger - the count of passengers in vehicle <br>
	 * registerNumber - the name register number vehicle <br>
	 * readyCrashCar - the status vehicle
	 */
	private String brand;
	private String type;
	private String nameModel;
	private Integer countOfPassenger;
	private String registerNumber;
	private Boolean readyCrashCar;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNameModel() {
		return nameModel;
	}

	public void setNameModel(String nameModel) {
		this.nameModel = nameModel;
	}

	public Integer getCountOfPassenger() {
		return countOfPassenger;
	}

	public void setCountOfPassenger(Integer countOfPassenger) {
		this.countOfPassenger = countOfPassenger;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String string) {
		this.registerNumber = string;
	}

	public Boolean getReadyCrashCar() {
		return readyCrashCar;
	}

	public void setReadyCrashCar(Boolean readyCrashCar) {
		this.readyCrashCar = readyCrashCar;
	}

}

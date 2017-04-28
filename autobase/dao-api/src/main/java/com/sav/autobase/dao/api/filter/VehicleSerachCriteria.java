package com.sav.autobase.dao.api.filter;

public class VehicleSerachCriteria {

	private String brand;
	private String type;
	private String nameModel;
	private Integer countOfPassenger;
	private Integer registerNumber;
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

	public Integer getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}

	public Boolean getReadyCrashCar() {
		return readyCrashCar;
	}

	public void setReadyCrashCar(Boolean readyCrashCar) {
		this.readyCrashCar = readyCrashCar;
	}

	public boolean isEmpty() {
		return brand == null
				&& type == null
				&& nameModel == null
				&& countOfPassenger == null
				&& registerNumber == null
				&& readyCrashCar == null;
	}

}

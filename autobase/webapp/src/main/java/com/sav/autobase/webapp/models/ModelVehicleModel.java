package com.sav.autobase.webapp.models;

public class ModelVehicleModel {

	private String nameModel;
	private String registerNumber;
	private Integer countOfPassenger;
	private TypeVehicleModel brand;
	private BrandVehicleModel type;

	public String getNameModel() {
		return nameModel;
	}

	public void setNameModel(String nameModel) {
		this.nameModel = nameModel;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public Integer getCountOfPassenger() {
		return countOfPassenger;
	}

	public void setCountOfPassenger(Integer countOfPassenger) {
		this.countOfPassenger = countOfPassenger;
	}

	public TypeVehicleModel getBrand() {
		return brand;
	}

	public void setBrand(TypeVehicleModel brand) {
		this.brand = brand;
	}

	public BrandVehicleModel getType() {
		return type;
	}

	public void setType(BrandVehicleModel type) {
		this.type = type;
	}

}

package com.sav.autobase.datamodel;

public class ModelVehicle extends AbstractModel {

	private String nameModel;
	private String registerNumber;
	private Integer countOfPassenger;
	private BrandVehicle brand;
	private TypeVehicle type;

	public BrandVehicle getBrand() {
		return brand;
	}

	public void setBrand(BrandVehicle brand) {
		this.brand = brand;
	}

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

	public TypeVehicle getType() {
		return type;
	}

	public void setType(TypeVehicle type) {
		this.type = type;
	}

	public Integer getCountOfPassenger() {
		return countOfPassenger;
	}

	public void setCountOfPassenger(Integer countOfPassenger) {
		this.countOfPassenger = countOfPassenger;
	}

	@Override
	public String toString() {
		return "ModelVehicle[id = " + getId() + ", " + brand + ", nameModel = " + nameModel + ", registerNumber = "
				+ registerNumber + ", " + type + ", countOfPassenger = " + countOfPassenger + "]";
	}

}

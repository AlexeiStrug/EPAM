package com.sav.autobase.webapp.models;

/**
 * This class ModelVehicleModel serves to store objects with properties model
 * vehicle
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class ModelVehicleModel extends IdModel {

	/**
	 * nameModel - the name model vehicle <br>
	 * registerNumber - the register number vehicle <br>
	 * countOfPassenger - the count of passengers in vehicle <br>
	 * brand - the name brand vehicle <br>
	 * type - the name type brand vehicle
	 */
	private String nameModel;
	private String registerNumber;
	private Integer countOfPassenger;
	private BrandVehicleModel brand;
	private TypeVehicleModel type;

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

	public BrandVehicleModel getBrand() {
		return brand;
	}

	public void setBrand(BrandVehicleModel brand) {
		this.brand = brand;
	}

	public TypeVehicleModel getType() {
		return type;
	}

	public void setType(TypeVehicleModel type) {
		this.type = type;
	}

}

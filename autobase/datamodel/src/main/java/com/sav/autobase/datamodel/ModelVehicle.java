package com.sav.autobase.datamodel;

public class ModelVehicle extends AbstractData {


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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((countOfPassenger == null) ? 0 : countOfPassenger.hashCode());
		result = prime * result + ((nameModel == null) ? 0 : nameModel.hashCode());
		result = prime * result + ((registerNumber == null) ? 0 : registerNumber.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof ModelVehicle))
			return false;
		ModelVehicle other = (ModelVehicle) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (countOfPassenger == null) {
			if (other.countOfPassenger != null)
				return false;
		} else if (!countOfPassenger.equals(other.countOfPassenger))
			return false;
		if (nameModel == null) {
			if (other.nameModel != null)
				return false;
		} else if (!nameModel.equals(other.nameModel))
			return false;
		if (registerNumber == null) {
			if (other.registerNumber != null)
				return false;
		} else if (!registerNumber.equals(other.registerNumber))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}

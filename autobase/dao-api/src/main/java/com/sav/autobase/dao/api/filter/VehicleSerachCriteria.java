package com.sav.autobase.dao.api.filter;

public class VehicleSerachCriteria {

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

	public void setRegisterNumber(String registerNumber) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((countOfPassenger == null) ? 0 : countOfPassenger.hashCode());
		result = prime * result + ((nameModel == null) ? 0 : nameModel.hashCode());
		result = prime * result + ((readyCrashCar == null) ? 0 : readyCrashCar.hashCode());
		result = prime * result + ((registerNumber == null) ? 0 : registerNumber.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof VehicleSerachCriteria))
			return false;
		VehicleSerachCriteria other = (VehicleSerachCriteria) obj;
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
		if (readyCrashCar == null) {
			if (other.readyCrashCar != null)
				return false;
		} else if (!readyCrashCar.equals(other.readyCrashCar))
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

	@Override
	public String toString() {
		return "VehicleSerachCriteria [brand=" + brand + ", type=" + type + ", nameModel=" + nameModel
				+ ", countOfPassenger=" + countOfPassenger + ", registerNumber=" + registerNumber + ", readyCrashCar="
				+ readyCrashCar + "]";
	}

	
	
	

}

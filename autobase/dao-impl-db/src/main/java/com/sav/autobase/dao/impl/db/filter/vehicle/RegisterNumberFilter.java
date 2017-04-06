package com.sav.autobase.dao.impl.db.filter.vehicle;

public class RegisterNumberFilter implements IFilterVehicle {

	private String regNumber;

	public RegisterNumberFilter(String regNumber) {
		this.regNumber = regNumber;
	}

	@Override
	public String generateCondition() {
		return "register_number = " + regNumber;
	}
}

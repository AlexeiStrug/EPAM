package com.sav.autobase.dao.impl.db.filter.vehicle;

public class BrandModelFilter implements IFilterVehicle {

	private String brand;

	public BrandModelFilter(String brand) {
		this.brand = brand;
	}

	@Override
	public String generateCondition() {
		return "brand_name = " + brand;
	}

}

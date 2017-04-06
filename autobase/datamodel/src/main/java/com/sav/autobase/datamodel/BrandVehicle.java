package com.sav.autobase.datamodel;

public class BrandVehicle extends AbstractModel{

	private String brandName;


	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Override
	public String toString() {
		return "brandName = " + brandName;
	}



}

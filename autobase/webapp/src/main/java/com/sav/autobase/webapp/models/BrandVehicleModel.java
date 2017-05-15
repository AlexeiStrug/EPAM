package com.sav.autobase.webapp.models;

/**
 * This class BrandVehicleModel serves to store objects with properties brand
 * vehicle
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class BrandVehicleModel extends IdModel {

	/**
	 * brandName - the name brand vehicle
	 */
	private String brandName;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}

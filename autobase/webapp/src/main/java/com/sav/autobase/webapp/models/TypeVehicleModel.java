package com.sav.autobase.webapp.models;

/**
 * This class BrandVehicleModel serves to store objects with properties type
 * vehicle
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class TypeVehicleModel extends IdModel {

	/**
	 * typeName - the name type vehicle
	 */
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}

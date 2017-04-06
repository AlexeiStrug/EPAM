package com.sav.autobase.datamodel;

public class TypeVehicle extends AbstractModel {
	

	private String typeName;

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "typeName = " + typeName;
	}
	
	

}

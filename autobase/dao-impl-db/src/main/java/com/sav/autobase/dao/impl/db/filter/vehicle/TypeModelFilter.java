package com.sav.autobase.dao.impl.db.filter.vehicle;

public class TypeModelFilter implements IFilterVehicle {

	private String type;
	
	
	public TypeModelFilter(String type) {
		this.type = type;
	}


	@Override
	public String generateCondition() {
		
		return "type_name = " + type;
	}
	

}

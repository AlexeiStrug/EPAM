package com.sav.autobase.dao.impl.db.filter.vehicle;

public class NameModelFilter implements IFilterVehicle {

	private String nameModel;

	public NameModelFilter(String nameModel) {
		this.nameModel = nameModel;
	}

	@Override
	public String generateCondition() {
		return "name_model =" + nameModel;
	}

}

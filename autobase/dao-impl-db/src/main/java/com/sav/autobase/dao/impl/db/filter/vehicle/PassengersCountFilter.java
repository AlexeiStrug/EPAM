package com.sav.autobase.dao.impl.db.filter.vehicle;

public class PassengersCountFilter implements IFilterVehicle{

	private int count;
	

	public PassengersCountFilter(int count) {
		this.count = count;
	}
	
	@Override
	public String generateCondition() {
		return "model_vehicle.count_of_passenger >=" + count;
	}
	

}

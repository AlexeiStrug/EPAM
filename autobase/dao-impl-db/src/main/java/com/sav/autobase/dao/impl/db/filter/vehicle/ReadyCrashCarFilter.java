package com.sav.autobase.dao.impl.db.filter.vehicle;

public class ReadyCrashCarFilter implements IFilterVehicle {

	private boolean readyCrashCar;

	public ReadyCrashCarFilter(Boolean readyCrashCar) {
		this.readyCrashCar = readyCrashCar;
	}

	@Override
	public String generateCondition() {

		return "ready_crash_car = " + readyCrashCar;
	}

}

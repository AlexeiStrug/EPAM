package com.sav.autobase.webapp.models;

public class DriverTripModel extends IdModel{

	private ClientRequestModel request;
	private VehicleModel vehicle;
	private boolean endTrip;

	public ClientRequestModel getRequest() {
		return request;
	}

	public void setRequest(ClientRequestModel request) {
		this.request = request;
	}

	public VehicleModel getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleModel vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isEndTrip() {
		return endTrip;
	}

	public void setEndTrip(boolean endTrip) {
		this.endTrip = endTrip;
	}

}

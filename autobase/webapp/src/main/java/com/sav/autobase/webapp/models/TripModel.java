package com.sav.autobase.webapp.models;

public class TripModel  extends IdModel{

	private RequestModel request;
	private VehicleModel vehicle;
	private Boolean endTrip;

	public RequestModel getRequest() {
		return request;
	}

	public void setRequest(RequestModel request) {
		this.request = request;
	}

	public VehicleModel getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleModel vehicle) {
		this.vehicle = vehicle;
	}

	public Boolean isEndTrip() {
		return endTrip;
	}

	public void setEndTrip(boolean endTrip) {
		this.endTrip = endTrip;
	}

}

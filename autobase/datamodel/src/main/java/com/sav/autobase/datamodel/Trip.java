package com.sav.autobase.datamodel;

public class Trip extends AbstractModel {


	private Request request;
	private Vehicle vehicle;
	private Boolean endTrip;
	
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Boolean getEndTrip() {
		return endTrip;
	}
	public void setEndTrip(Boolean endTrip) {
		this.endTrip = endTrip;
	}
	@Override
	public String toString() {
		return "Trip id=" + getId()+ " [request=" + request + ", vehicle=" + vehicle + ", endTrip=" + endTrip + "]";
	}
	 
	
	

	
}

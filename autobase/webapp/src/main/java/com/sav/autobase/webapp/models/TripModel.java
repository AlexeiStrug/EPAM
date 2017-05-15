package com.sav.autobase.webapp.models;

/**
 * This class TripModel serves to store objects with properties trip from
 * dispatcher or administrator
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class TripModel extends IdModel {

	/**
	 * request - the request information from client <br>
	 * vehicle - the vehicle driver <br>
	 * endTrip - status trip
	 */
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

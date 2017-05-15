package com.sav.autobase.webapp.models;

/**
 * This class DriverTripModel serves to store objects with properties trip from
 * user driver
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class DriverTripModel extends IdModel {

	/**
	 * request - the request information from client <br>
	 * vehicle - the vehicle driver <br>
	 * endTrip - status trip
	 */
	private ClientRequestModel request;
	private VehicleModel vehicle;
	private Boolean endTrip;

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

	public Boolean isEndTrip() {
		return endTrip;
	}

	public void setEndTrip(Boolean endTrip) {
		this.endTrip = endTrip;
	}

}

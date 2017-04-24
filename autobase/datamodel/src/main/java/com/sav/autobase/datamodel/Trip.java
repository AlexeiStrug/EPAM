package com.sav.autobase.datamodel;

public class Trip extends AbstractData {

	private Request request;
	private Vehicle vehicle;
	private boolean endTrip;
	
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

	public boolean isEndTrip() {
		return endTrip;
	}
	public void setEndTrip(boolean endTrip) {
		this.endTrip = endTrip;
	}
	@Override
	public String toString() {
		return "Trip id=" + getId()+ " [request=" + request + ", vehicle=" + vehicle + ", endTrip=" + endTrip + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (endTrip ? 1231 : 1237);
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Trip))
			return false;
		Trip other = (Trip) obj;
		if (endTrip != other.endTrip)
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}
	 
	
	

	
}

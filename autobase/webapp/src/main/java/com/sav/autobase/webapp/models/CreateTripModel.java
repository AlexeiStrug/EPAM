package com.sav.autobase.webapp.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class CreateTripModel {

	private RequestModel request;
	private VehicleModel vehicle;

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

	public ResponseEntity<?> createTrip(@RequestBody CreateTripModel m) {
		
		if (m == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}

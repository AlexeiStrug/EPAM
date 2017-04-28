package com.sav.autobase.webapp.models;

public class PlaceModel extends IdModel {

	private String placeStart;
	private String placeEnd;
	private Integer distance;

	public String getPlaceStart() {
		return placeStart;
	}

	public void setPlaceStart(String placeStart) {
		this.placeStart = placeStart;
	}

	public String getPlaceEnd() {
		return placeEnd;
	}

	public void setPlaceEnd(String placeEnd) {
		this.placeEnd = placeEnd;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

}

package com.sav.autobase.webapp.models;

/**
 * This class PlaceModel serves to store objects with properties place
 * 
 * @author AlexStrug
 * @version 1
 *
 */
public class PlaceModel extends IdModel {

	/**
	 * placeStart - the place start <br>
	 * placeEnd - the place end <br>
	 * distance - the distance trip
	 */
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

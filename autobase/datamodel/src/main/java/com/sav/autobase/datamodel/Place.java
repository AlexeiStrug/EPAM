package com.sav.autobase.datamodel;

public class Place extends AbstractModel{

	private String placeStart;
	private String placeEnd;
	private Integer distance;

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

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

	@Override
	public String toString() {
		return "[placeStart=" + placeStart + ", placeEnd=" + placeEnd + ", distance=" + distance + "]";
	}

	

}

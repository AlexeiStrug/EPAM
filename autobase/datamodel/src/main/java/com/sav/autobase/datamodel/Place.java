package com.sav.autobase.datamodel;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Place extends AbstractModel {

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
		return toStringWithAttributes();
	}

	public String toStringWithAttributes() {
		Object myself = this;
		ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) {

			@Override
			protected boolean accept(Field field) {
				try {
					return super.accept(field) && field.get(myself) != null;
				} catch (IllegalAccessException e) {
					return super.accept(field);
				}
			}

		};

		return builder.toString();

	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((placeEnd == null) ? 0 : placeEnd.hashCode());
		result = prime * result + ((placeStart == null) ? 0 : placeStart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Place))
			return false;
		Place other = (Place) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (placeEnd == null) {
			if (other.placeEnd != null)
				return false;
		} else if (!placeEnd.equals(other.placeEnd))
			return false;
		if (placeStart == null) {
			if (other.placeStart != null)
				return false;
		} else if (!placeStart.equals(other.placeStart))
			return false;
		return true;
	}

}

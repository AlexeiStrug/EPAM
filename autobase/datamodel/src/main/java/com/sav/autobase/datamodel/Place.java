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

}

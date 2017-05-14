package com.sav.autobase.datamodel;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Vehicle extends AbstractData implements Serializable{

	private Users driver;
	private ModelVehicle model;
	private boolean readyCrashCar;

	public Users getDriver() {
		return driver;
	}

	public void setDriver(Users driver) {
		this.driver = driver;
	}

	public ModelVehicle getModel() {
		return model;
	}

	public void setModel(ModelVehicle model) {
		this.model = model;
	}

	public boolean isReadyCrashCar() {
		return readyCrashCar;
	}

	public void setReadyCrashCar(boolean readyCrashCar) {
		this.readyCrashCar = readyCrashCar;
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
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (readyCrashCar ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Vehicle))
			return false;
		Vehicle other = (Vehicle) obj;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (readyCrashCar != other.readyCrashCar)
			return false;
		return true;
	}

}

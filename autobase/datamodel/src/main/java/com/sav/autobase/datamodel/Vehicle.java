package com.sav.autobase.datamodel;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Vehicle extends AbstractModel{

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

}

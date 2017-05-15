package com.sav.autobase.services.factory;

import java.text.ParseException;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;

public abstract class AbstractFactory {

	public abstract Users createUser();

	public abstract Place createPlace();

	public abstract Request createRequest(Users user, Place place) throws ParseException;
	
	public abstract Request createClientRequest(Users user, Place place) throws ParseException;

	public abstract BrandVehicle createBrand();

	public abstract TypeVehicle createType();

	public abstract ModelVehicle createModel(BrandVehicle brand, TypeVehicle type);

	public abstract Vehicle createVehicle(ModelVehicle model, Users user);
	
	public abstract Trip createTrip(Vehicle vehicle, Request request);

	

}

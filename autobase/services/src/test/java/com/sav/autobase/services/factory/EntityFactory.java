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

public class EntityFactory extends AbstractFactory {
	
	private UserFactory usersFactory;
	private PlaceFactory placeFactory;
	private BrandFactory brandFactory;
	private TypeFactory typeFactory;

	public EntityFactory() {
		usersFactory = new UserFactory();
		placeFactory = new PlaceFactory();
		brandFactory = new BrandFactory();
		typeFactory = new TypeFactory();
	}

	@Override
	public Users createUser() {
		return usersFactory.create();
	}

	@Override
	public Place createPlace() {
		return placeFactory.create();
	}
	

	@Override
	public Request createRequest(Users user, Place place) throws ParseException {
		return (new RequestFactory(user, place).create()); 
	}

	@Override
	public BrandVehicle createBrand() {
		return brandFactory.create();
	}

	@Override
	public TypeVehicle createType() {
		return typeFactory.create();
	}

	@Override
	public ModelVehicle createModel(BrandVehicle brand, TypeVehicle type) {
		return (new ModelFactory(brand, type).create());
	}

	@Override
	public Vehicle createVehicle(ModelVehicle model, Users user) {
		return (new VehicleFactory(model, user).create());
	}

	@Override
	public Trip createTrip(Vehicle vehicle, Request request) {
		return (new TripFactory(request, vehicle).create());
	}


}

package com.sav.autobase.webapp.controllers;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.impl.DriverService;
import com.sav.autobase.webapp.models.BrandVehicleModel;
import com.sav.autobase.webapp.models.ClientRequestModel;
import com.sav.autobase.webapp.models.ClientUsersModel;
import com.sav.autobase.webapp.models.DriverTripModel;
import com.sav.autobase.webapp.models.DriverUsersModel;
import com.sav.autobase.webapp.models.IdModel;
import com.sav.autobase.webapp.models.ModelVehicleModel;
import com.sav.autobase.webapp.models.PlaceModel;
import com.sav.autobase.webapp.models.TripModel;
import com.sav.autobase.webapp.models.TypeVehicleModel;
import com.sav.autobase.webapp.models.VehicleModel;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Inject
	private DriverService driverService;

	@RequestMapping(value = "/trip/{users}", method = RequestMethod.GET)
	public ResponseEntity<?> getTrip(@PathVariable(value = "users") Users userParam) throws DAOException {

		Trip trip = driverService.getTrip(userParam);
		DriverTripModel tripModel = trip2model(trip);
		return new ResponseEntity<DriverTripModel>(tripModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle/{users}", method = RequestMethod.GET)
	public ResponseEntity<?> getVehicle(@PathVariable(value = "users") Users userParam) throws DAOException {

		Vehicle vehicle = driverService.getVehicle(userParam);
		VehicleModel vehicleModel = vehicle2model(vehicle);
		return new ResponseEntity<VehicleModel>(vehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip/{users}", method = RequestMethod.PUT)
	public ResponseEntity<?> changeStatusTrip(@PathVariable(value = "users") Users userParam) throws DAOException {

		driverService.changeStatusTrip(userParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	@RequestMapping(value = "/request/{users}", method = RequestMethod.PUT)
	public ResponseEntity<?> changeStatusVehicle(@PathVariable(value = "users") Users userParam) throws DAOException {

		driverService.changeStatusVehicle(userParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ---Convert model to entity and entity to model--- */
	private DriverTripModel trip2model(Trip trip) {
		DriverTripModel tripModel = new DriverTripModel();
		tripModel.setRequest(request2model(trip.getRequest()));
		tripModel.setVehicle(vehicle2model(trip.getVehicle()));
		tripModel.setEndTrip(trip.isEndTrip());
		return tripModel;
	}

	private Trip model2trip(DriverTripModel tripModel) {
		Trip trip = new Trip();
		trip.setRequest(model2request(tripModel.getRequest()));
		trip.setVehicle(model2vehicle(tripModel.getVehicle()));
		trip.setEndTrip(tripModel.isEndTrip());
		return trip;
	}

	private ClientRequestModel request2model(Request request) {
		ClientRequestModel requestModel = new ClientRequestModel();
		requestModel.setClient(client2model(request.getClient()));
		requestModel.setStartDate(request.getStartDate());
		requestModel.setEndDate(request.getEndDate());
		requestModel.setPlace(place2model(request.getPlace()));
		requestModel.setCountOfPassenger(request.getCountOfPassenger());
		requestModel.setProcessed(request.getProcessed().name());
		return requestModel;
	}

	private Request model2request(ClientRequestModel requestModel) {
		Request request = new Request();
		request.setClient(model2client(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(model2place(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));

		return request;
	}

	private ClientUsersModel client2model(Users user) {
		ClientUsersModel userModel = new ClientUsersModel();
		userModel.setFirstName(userModel.getFirstName());
		userModel.setLastName(userModel.getLastName());
		userModel.setDateBirth(userModel.getDateBirth());
		userModel.setLogin(userModel.getLogin());
		userModel.setEmail(userModel.getEmail());
		return userModel;

	}

	private Users model2client(ClientUsersModel userModel) {
		Users user = new Users();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setDateBirth(userModel.getDateBirth());
		user.setLogin(userModel.getLogin());
		user.setEmail(userModel.getEmail());
		return user;

	}

	private PlaceModel place2model(Place place) {
		PlaceModel placeModel = new PlaceModel();
		placeModel.setPlaceStart(place.getPlaceStart());
		placeModel.setPlaceEnd(place.getPlaceEnd());
		placeModel.setDistance(place.getDistance());
		return placeModel;
	}

	private Place model2place(PlaceModel placeModel) {
		Place place = new Place();
		place.setPlaceStart(placeModel.getPlaceStart());
		place.setPlaceEnd(placeModel.getPlaceEnd());
		place.setDistance(placeModel.getDistance());
		return place;
	}

	private VehicleModel vehicle2model(Vehicle vehicle) {
		VehicleModel vehicleModel = new VehicleModel();
		vehicleModel.setDriver(driver2model(vehicle.getDriver()));
		vehicleModel.setModel(modelVehicle2model(vehicle.getModel()));
		vehicleModel.setReadyCrashCar(vehicle.isReadyCrashCar());
		return vehicleModel;

	}

	private Vehicle model2vehicle(VehicleModel vehicleModel) {
		Vehicle vehicle = new Vehicle();
		vehicle.setDriver(model2driver(vehicleModel.getDriver()));
		vehicle.setModel(model2modelVehicle(vehicleModel.getModel()));
		vehicle.setReadyCrashCar(vehicleModel.isReadyCrashCar());
		return vehicle;
	}

	private ModelVehicleModel modelVehicle2model(ModelVehicle modelVehicle) {
		ModelVehicleModel modelVehicleModel = new ModelVehicleModel();
		modelVehicleModel.setNameModel(modelVehicle.getNameModel());
		modelVehicleModel.setRegisterNumber(modelVehicle.getRegisterNumber());
		modelVehicleModel.setCountOfPassenger(modelVehicle.getCountOfPassenger());
		modelVehicleModel.setBrand(brand2model(modelVehicle.getBrand()));
		modelVehicleModel.setType(type2model(modelVehicle.getType()));
		return modelVehicleModel;
	}

	private ModelVehicle model2modelVehicle(ModelVehicleModel modelVehicleModel) {
		ModelVehicle modelVehicle = new ModelVehicle();
		modelVehicle.setNameModel(modelVehicleModel.getNameModel());
		modelVehicle.setRegisterNumber(modelVehicleModel.getRegisterNumber());
		modelVehicle.setCountOfPassenger(modelVehicleModel.getCountOfPassenger());
		modelVehicle.setBrand(model2brand(modelVehicleModel.getBrand()));
		modelVehicle.setType(model2type(modelVehicleModel.getType()));
		return modelVehicle;
	}

	private TypeVehicleModel type2model(TypeVehicle type) {
		TypeVehicleModel typeModel = new TypeVehicleModel();
		typeModel.setTypeName(type.getTypeName());
		return typeModel;
	}

	private TypeVehicle model2type(TypeVehicleModel typeModel) {
		TypeVehicle type = new TypeVehicle();
		type.setTypeName(typeModel.getTypeName());
		return type;
	}

	private BrandVehicleModel brand2model(BrandVehicle brand) {
		BrandVehicleModel brandModel = new BrandVehicleModel();
		brandModel.setBrandName(brand.getBrandName());
		return brandModel;
	}

	private BrandVehicle model2brand(BrandVehicleModel brandModel) {
		BrandVehicle brand = new BrandVehicle();
		brand.setBrandName(brandModel.getBrandName());
		return brand;
	}

	private DriverUsersModel driver2model(Users user) {
		DriverUsersModel userModel = new DriverUsersModel();
		userModel.setFirstName(userModel.getFirstName());
		userModel.setLastName(userModel.getLastName());
		userModel.setLogin(userModel.getLogin());
		return userModel;
	}

	private Users model2driver(DriverUsersModel userModel) {
		Users user = new Users();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setLogin(userModel.getLogin());
		return user;
	}
}

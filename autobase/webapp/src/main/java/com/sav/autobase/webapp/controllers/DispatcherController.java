package com.sav.autobase.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.impl.DispatcherService;
import com.sav.autobase.webapp.models.BrandVehicleModel;
import com.sav.autobase.webapp.models.ClientRequestModel;
import com.sav.autobase.webapp.models.ClientUsersModel;
import com.sav.autobase.webapp.models.DispatcherUsersModel;
import com.sav.autobase.webapp.models.DriverUsersModel;
import com.sav.autobase.webapp.models.IdModel;
import com.sav.autobase.webapp.models.ModelVehicleModel;
import com.sav.autobase.webapp.models.PlaceModel;
import com.sav.autobase.webapp.models.RequestModel;
import com.sav.autobase.webapp.models.TripModel;
import com.sav.autobase.webapp.models.TypeVehicleModel;
import com.sav.autobase.webapp.models.VehicleModel;

@RestController
@RequestMapping("/dispatcher")
public class DispatcherController {

	@Inject
	private DispatcherService dispatcherService;

	@RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdRequest(@PathVariable(value = "id") Integer requestIdParam) throws DAOException {

		Request request = dispatcherService.getRequest(requestIdParam);
		RequestModel requestModel = request2model(request);
		return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/request/{users}", method = RequestMethod.POST)
	public ResponseEntity<?> getRequestByStatus(@PathVariable(value = "users") Users userParam) throws DAOException {

		Request request = dispatcherService.getRequestByStatus(userParam);
		RequestModel requestModel = request2model(request);
		return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
	}

	// @RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
	// public ResponseEntity<?> findByStatusRequest(@RequestParam(required =
	// false) StatusRequest status)
	// throws DAOException {
	//
	// Request request = new Request();
	// try {
	// request = dispatcherService.findByStatus(status);
	// } catch (IllegalArgumentException e) {
	// String msg = String.format("Status [%s] request is not supported. Please
	// use one of: %s", status,
	// StatusRequest.values());
	// return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	// }
	//
	// RequestModel requestModel = request2model(request);
	// return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
	// }

	@RequestMapping(value = "/request/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@RequestBody RequestModel requestModel,
			@PathVariable(value = "id") Integer requestIdParam) throws DAOException, ModifyException {

		Request request = dispatcherService.getRequest(requestIdParam);
		request.setClient(model2client(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(model2place(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setDispatcher(model2dispatcher(requestModel.getDispatcher()));
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));

		dispatcherService.modifyRequest(request);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTrip(@PathVariable(value = "id") Integer tripIdParam) throws DAOException {

		Trip trip = dispatcherService.getTrip(tripIdParam);
		TripModel tripModel = trip2model(trip);
		return new ResponseEntity<TripModel>(tripModel, HttpStatus.OK);
	}

	/* подумать над параметром вывод всех рейсов готовых или нет */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TripModel>> getAllTrip() throws DAOException {

		List<Trip> getAll = dispatcherService.getAllTrip();

		List<TripModel> tripModel = new ArrayList<>();
		for (Trip trip : getAll) {
			tripModel.add(trip2model(trip));
		}

		return new ResponseEntity<List<TripModel>>(tripModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{request},{vehicle}", method = RequestMethod.POST)
	public ResponseEntity<?> createTrip(@RequestBody TripModel tripModel, @PathVariable(value = "request,vehicle") Request requestParam, Vehicle vehicleParam)
			throws DAOException, ModifyException {

		Trip trip = model2trip(tripModel);
		dispatcherService.createTrip(requestParam, vehicleParam);
		
		return new ResponseEntity<IdModel>(new IdModel(trip.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@RequestBody TripModel tripModel,
			@PathVariable(value = "id") Integer tripIdParam) throws DAOException, ModifyException {

		Trip trip = new Trip();
		trip.setRequest(model2request(tripModel.getRequest()));
		trip.setVehicle(model2vehicle(tripModel.getVehicle()));
		trip.setEndTrip(tripModel.isEndTrip());

		dispatcherService.modifyTrip(trip);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Integer tripIdParam) throws DAOException {
		dispatcherService.deleteTrip(tripIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ---Convert model to entity and entity to model--- */
	private RequestModel request2model(Request request) {
		RequestModel requestModel = new RequestModel();
		requestModel.setClient(client2model(request.getClient()));
		requestModel.setStartDate(request.getStartDate());
		requestModel.setEndDate(request.getEndDate());
		requestModel.setPlace(place2model(request.getPlace()));
		requestModel.setCountOfPassenger(request.getCountOfPassenger());
		requestModel.setDispatcher(dispatcher2model(request.getDispatcher()));
		requestModel.setProcessed(request.getProcessed().name());
		return requestModel;
	}

	private Request model2request(RequestModel requestModel) {
		Request request = new Request();
		request.setClient(model2client(requestModel.getClient()));
		request.setStartDate(requestModel.getStartDate());
		request.setEndDate(requestModel.getEndDate());
		request.setPlace(model2place(requestModel.getPlace()));
		request.setCountOfPassenger(requestModel.getCountOfPassenger());
		request.setDispatcher(model2dispatcher(requestModel.getDispatcher()));
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

	private DispatcherUsersModel dispatcher2model(Users user) {
		DispatcherUsersModel userModel = new DispatcherUsersModel();
		userModel.setFirstName(userModel.getFirstName());
		userModel.setLogin(userModel.getLogin());
		userModel.setEmail(userModel.getEmail());
		userModel.setType(user.getType().name());
		return userModel;

	}

	private Users model2dispatcher(DispatcherUsersModel userModel) {
		Users user = new Users();
		user.setFirstName(userModel.getFirstName());
		user.setLogin(userModel.getLogin());
		user.setEmail(userModel.getEmail());
		user.setType(TypeUsers.valueOf(userModel.getType()));
		return user;

	}

	private TripModel trip2model(Trip trip) {
		TripModel tripModel = new TripModel();
		tripModel.setRequest(request2model(trip.getRequest()));
		tripModel.setVehicle(vehicle2model(trip.getVehicle()));
		tripModel.setEndTrip(trip.isEndTrip());
		return tripModel;
	}

	private Trip model2trip(TripModel tripModel) {
		Trip trip = new Trip();
		trip.setRequest(model2request(tripModel.getRequest()));
		trip.setVehicle(model2vehicle(tripModel.getVehicle()));
		trip.setEndTrip(tripModel.isEndTrip());
		return trip;
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

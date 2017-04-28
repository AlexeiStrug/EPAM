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
import org.springframework.web.bind.annotation.RestController;

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
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
import com.sav.autobase.services.IDispatcherService;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.webapp.models.BrandVehicleModel;
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
import com.sav.autobase.webapp.models.VehicleSearchModel;

@RestController
@RequestMapping("/dispatcher")
public class DispatcherController {

	@Inject
	private IDispatcherService dispatcherService;

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

	@RequestMapping(value = "/request/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@RequestBody RequestModel requestModel,
			@PathVariable(value = "id") Integer requestIdParam) throws DAOException, ModifyException {

		Request request = dispatcherService.getRequest(requestIdParam);

		requestApplyChanges(request, requestModel);

		dispatcherService.modifyRequest(request);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	public void requestApplyChanges(Request requestFromDb, RequestModel requestModel)
			throws DAOException, ModifyException {

		if (requestModel.getClient() != null) {
			requestFromDb.setClient(model2client(requestModel.getClient()));
		}
		if (requestModel.getStartDate() != null) {
			requestFromDb.setStartDate(requestModel.getStartDate());
		}
		if (requestModel.getEndDate() != null) {
			requestFromDb.setEndDate(requestModel.getEndDate());
		}
		if (requestModel.getPlace() != null) {
			requestFromDb.setPlace(model2place(requestModel.getPlace()));
		}
		if (requestModel.getCountOfPassenger() != null) {
			requestFromDb.setCountOfPassenger(requestModel.getCountOfPassenger());
		}
		if (requestModel.getComment() != null) {
			requestFromDb.setComment(requestModel.getComment());
		}
		if (requestModel.getProcessed() != null) {
			requestFromDb.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));
		}
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

	@RequestMapping(value = "/trip", method = RequestMethod.PUT)
	public ResponseEntity<?> createTrip(@RequestBody RequestModel requestModel, @RequestBody VehicleModel vehicleModel)
			throws DAOException, ModifyException {

		Request request = model2request(requestModel);
		Vehicle vehicle = model2vehicle(vehicleModel);
		Trip trip = dispatcherService.createTrip(request, vehicle);

		return new ResponseEntity<IdModel>(new IdModel(trip.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@RequestBody TripModel tripModel,
			@PathVariable(value = "id") Integer tripIdParam) throws DAOException, ModifyException {

		Trip trip = dispatcherService.getTrip(tripIdParam);

		tripApplyChanges(trip, tripModel);

		dispatcherService.modifyTrip(trip);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	public void tripApplyChanges(Trip tripFromDb, TripModel tripModel) throws DAOException, ModifyException {

		if (tripModel.getRequest() != null) {
			tripFromDb.setRequest(model2request(tripModel.getRequest()));
		}
		if (tripModel.getVehicle() != null) {
			tripFromDb.setVehicle(model2vehicle(tripModel.getVehicle()));
		}
		if (tripModel.isEndTrip() != null) {
			tripFromDb.setEndTrip(tripModel.isEndTrip());
		}
	}

	@RequestMapping(value = "/vehicle/criteria")
	public ResponseEntity<?> findVehicleByCriteria(@RequestBody VehicleSearchModel criteriaModel) throws DAOException {

		VehicleSerachCriteria criteria = model2vehicleSearch(criteriaModel);
		List<Vehicle> allCriteria = dispatcherService.findByCriteria(criteria);

		List<VehicleModel> convertCriteria = new ArrayList<>();
		for (Vehicle vehicle : allCriteria) {
			convertCriteria.add(vehicle2model(vehicle));
		}

		return new ResponseEntity<List<VehicleModel>>(convertCriteria, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrip(@PathVariable(value = "id") Integer tripIdParam) throws DAOException {

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
		requestModel.setComment(request.getComment());
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
		request.setComment(requestModel.getComment());
		request.setProcessed(StatusRequest.valueOf(requestModel.getProcessed()));

		return request;
	}

	private ClientUsersModel client2model(Users user) {
		ClientUsersModel userModel = new ClientUsersModel();
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setDateBirth(user.getDateBirth());
		userModel.setLogin(user.getLogin());
		userModel.setEmail(user.getEmail());
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
		userModel.setFirstName(user.getFirstName());
		userModel.setLogin(user.getLogin());
		userModel.setEmail(user.getEmail());
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
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setLogin(user.getLogin());
		return userModel;
	}

	private Users model2driver(DriverUsersModel userModel) {
		Users user = new Users();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setLogin(userModel.getLogin());
		return user;
	}

	private VehicleSearchModel vehicleSearch2model(VehicleSerachCriteria criteria) {
		VehicleSearchModel criteriaModel = new VehicleSearchModel();
		criteriaModel.setBrand(criteria.getBrand());
		criteriaModel.setType(criteria.getType());
		criteriaModel.setNameModel(criteria.getNameModel());
		criteriaModel.setCountOfPassenger(criteria.getCountOfPassenger());
		criteriaModel.setRegisterNumber(criteria.getRegisterNumber());
		criteriaModel.setReadyCrashCar(criteria.getReadyCrashCar());
		return criteriaModel;
	}

	private VehicleSerachCriteria model2vehicleSearch(VehicleSearchModel criteriaModel) {
		VehicleSerachCriteria criteria = new VehicleSerachCriteria();
		criteria.setBrand(criteriaModel.getBrand());
		criteria.setType(criteriaModel.getType());
		criteria.setNameModel(criteriaModel.getNameModel());
		criteria.setCountOfPassenger(criteriaModel.getCountOfPassenger());
		criteria.setRegisterNumber(criteriaModel.getRegisterNumber());
		criteria.setReadyCrashCar(criteriaModel.getReadyCrashCar());
		return criteria;
	}
}

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
import com.sav.autobase.services.IAdminService;
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
import com.sav.autobase.webapp.models.UsersModel;
import com.sav.autobase.webapp.models.VehicleModel;
import com.sav.autobase.webapp.models.VehicleSearchModel;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Inject
	private IAdminService adminService;

	/* ----All methods on Vehicle---- */
	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdVehicle(@PathVariable(value = "id") Integer vehicleIdParam) throws DAOException {

		Vehicle vehicle = adminService.getVehicle(vehicleIdParam);
		VehicleModel vehicleModel = vehicle2model(vehicle);

		return new ResponseEntity<VehicleModel>(vehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleModel>> getAllVehicle() throws DAOException {

		List<Vehicle> getAll = adminService.getAllVehicle();

		List<VehicleModel> vehicleModel = new ArrayList<>();
		for (Vehicle vehicle : getAll) {
			vehicleModel.add(vehicle2model(vehicle));
		}

		return new ResponseEntity<List<VehicleModel>>(vehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.PUT)
	public ResponseEntity<?> saveVehicle(@RequestBody VehicleModel vehicleModel) throws DAOException, ModifyException {

		Vehicle vehicle = model2vehicle(vehicleModel);
		adminService.saveVehicle(vehicle);

		return new ResponseEntity<IdModel>(new IdModel(vehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Integer vehicleIdParam) throws DAOException {

		adminService.deleteVehicle(vehicleIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Model Vehicle---- */
	@RequestMapping(value = "/modelvehicle/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdModelVehicle(@PathVariable(value = "id") Integer modelVehicleIdParam)
			throws DAOException {

		ModelVehicle modelVehicle = adminService.getModelVehicle(modelVehicleIdParam);
		ModelVehicleModel modelVehicleModel = modelVehicle2model(modelVehicle);

		return new ResponseEntity<ModelVehicleModel>(modelVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/modelvehicle", method = RequestMethod.GET)
	public ResponseEntity<List<ModelVehicleModel>> getAllModelVehicle() throws DAOException {

		List<ModelVehicle> getAll = adminService.getAllModelVehicle();

		List<ModelVehicleModel> modelVehicleModel = new ArrayList<>();
		for (ModelVehicle modelVehicle : getAll) {
			modelVehicleModel.add(modelVehicle2model(modelVehicle));
		}

		return new ResponseEntity<List<ModelVehicleModel>>(modelVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/modelvehicle", method = RequestMethod.PUT)
	public ResponseEntity<?> saveModelVehicle(@RequestBody ModelVehicleModel modelVehicleModel)
			throws DAOException, ModifyException {

		ModelVehicle modelVehicle = model2modelVehicle(modelVehicleModel);
		adminService.saveModel(modelVehicle);

		return new ResponseEntity<IdModel>(new IdModel(modelVehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/modelvehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteModelVehicle(@PathVariable(value = "id") Integer modelVehicleIdParam)
			throws DAOException {

		adminService.deleteModel(modelVehicleIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Brand Vehicle---- */
	@RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdBrandVehicle(@PathVariable(value = "id") Integer brandIdParam) throws DAOException {

		BrandVehicle brandVehicle = adminService.getBrand(brandIdParam);
		BrandVehicleModel brandVehicleModel = brand2model(brandVehicle);

		return new ResponseEntity<BrandVehicleModel>(brandVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/brand", method = RequestMethod.PUT)
	public ResponseEntity<?> saveBrandVehicle(@RequestBody BrandVehicleModel brandVehicleModel)
			throws DAOException, ModifyException {

		BrandVehicle brandVehicle = model2brand(brandVehicleModel);
		adminService.addNewBrand(brandVehicle);

		return new ResponseEntity<IdModel>(new IdModel(brandVehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/brand/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBrandVehicle(@PathVariable(value = "id") Integer brandIdParam) throws DAOException {

		adminService.deleteBrand(brandIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Type Vehicle---- */
	@RequestMapping(value = "/type/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTypeVehicle(@PathVariable(value = "id") Integer typeIdParam) throws DAOException {

		TypeVehicle typeVehicle = adminService.getType(typeIdParam);
		TypeVehicleModel typeVehicleModel = type2model(typeVehicle);

		return new ResponseEntity<TypeVehicleModel>(typeVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/type", method = RequestMethod.PUT)
	public ResponseEntity<?> saveTypeVehicle(@RequestBody TypeVehicleModel typeVehicleModel)
			throws DAOException, ModifyException {

		TypeVehicle typeVehicle = model2type(typeVehicleModel);
		adminService.addNewType(typeVehicle);

		return new ResponseEntity<IdModel>(new IdModel(typeVehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTypeVehicle(@PathVariable(value = "id") Integer typeIdParam) throws DAOException {

		adminService.deleteType(typeIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on User---- */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdUser(@PathVariable(value = "id") Integer userIdParam) throws DAOException {

		Users user = adminService.getUser(userIdParam);
		UsersModel userModel = user2model(user);

		return new ResponseEntity<UsersModel>(userModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UsersModel>> getAllUser() throws DAOException {

		List<Users> getAll = adminService.getAllUser();

		List<UsersModel> userModel = new ArrayList<>();
		for (Users user : getAll) {
			userModel.add(user2model(user));
		}

		return new ResponseEntity<List<UsersModel>>(userModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseEntity<?> saveUser(@RequestBody UsersModel userModel) throws DAOException, ModifyException {

		Users user = model2user(userModel);
		adminService.saveUser(user);

		return new ResponseEntity<IdModel>(new IdModel(user.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userIdParam) throws DAOException {

		adminService.deleteUser(userIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Place---- */
	@RequestMapping(value = "/place/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdPlace(@PathVariable(value = "id") Integer placeIdParam) throws DAOException {

		Place place = adminService.getPlace(placeIdParam);
		PlaceModel placeModel = place2model(place);

		return new ResponseEntity<PlaceModel>(placeModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public ResponseEntity<List<PlaceModel>> getAllPlace() throws DAOException {

		List<Place> getAll = adminService.getAllPlace();

		List<PlaceModel> placeModel = new ArrayList<>();
		for (Place place : getAll) {
			placeModel.add(place2model(place));
		}

		return new ResponseEntity<List<PlaceModel>>(placeModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/place", method = RequestMethod.PUT)
	public ResponseEntity<?> savePlace(@RequestBody PlaceModel placeModel) throws DAOException, ModifyException {

		Place place = model2place(placeModel);
		adminService.savePlace(place);

		return new ResponseEntity<IdModel>(new IdModel(place.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/place/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePlace(@PathVariable(value = "id") Integer placeIdParam) throws DAOException {

		adminService.deletePlace(placeIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}
	
	/* ----All methods on Trip---- */
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTrip(@PathVariable(value = "id") Integer tripIdParam) throws DAOException {

		Trip trip = adminService.getTrip(tripIdParam);
		TripModel tripModel = trip2model(trip);

		return new ResponseEntity<TripModel>(tripModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip", method = RequestMethod.GET)
	public ResponseEntity<List<TripModel>> getAllTrip() throws DAOException {

		List<Trip> getAll = adminService.getAllTrip();

		List<TripModel> tripModel = new ArrayList<>();
		for (Trip trip : getAll) {
			tripModel.add(trip2model(trip));
		}

		return new ResponseEntity<List<TripModel>>(tripModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip", method = RequestMethod.PUT)
	public ResponseEntity<?> saveTrip(@RequestBody TripModel tripModel) throws DAOException, ModifyException {

		Trip trip = model2trip(tripModel);
		adminService.saveTrip(trip);

		return new ResponseEntity<IdModel>(new IdModel(trip.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrip(@PathVariable(value = "id") Integer tripIdParam) throws DAOException {

		adminService.deleteTrip(tripIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}
	
	/* ----All methods on Request---- */
	@RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdRequest(@PathVariable(value = "id") Integer requestIdParam) throws DAOException {

		Request request = adminService.getRequest(requestIdParam);
		RequestModel requestModel = request2model(request);

		return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllRequest() throws DAOException {

		List<Request> getAll = adminService.getAllRequest();

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAll) {
			requestModel.add(request2model(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/request/{users}", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllRequestByUser(@RequestBody UsersModel userModel) throws DAOException {

		Users user = model2user(userModel);
		List<Request> getAll = adminService.getAllRequestByUser(user);

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAll) {
			requestModel.add(request2model(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/request", method = RequestMethod.PUT)
	public ResponseEntity<?> saveRequest(@RequestBody RequestModel requestModel) throws DAOException, ModifyException {

		Request reqeust = model2request(requestModel);
		adminService.saveRequest(reqeust);

		return new ResponseEntity<IdModel>(new IdModel(reqeust.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Integer reqeustIdParam) throws DAOException {

		adminService.deleteRequest(reqeustIdParam);

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

	private UsersModel user2model(Users user) {
		UsersModel userModel = new UsersModel();
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setDateBirth(user.getDateBirth());
		userModel.setLogin(user.getLogin());
		userModel.setPassword(user.getPassword());
		userModel.setEmail(user.getEmail());
		userModel.setType(user.getType().name());
		return userModel;

	}

	private Users model2user(UsersModel userModel) {
		Users user = new Users();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setDateBirth(userModel.getDateBirth());
		user.setLogin(userModel.getLogin());
		user.setPassword(userModel.getPassword());
		user.setEmail(userModel.getEmail());
		user.setType(TypeUsers.valueOf(userModel.getType()));
		return user;

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

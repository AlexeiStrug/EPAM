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

import com.sav.autobase.dao.api.filter.UserSearchCriteria;
import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IAdminService;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.exception.ServiceException;
import com.sav.autobase.webapp.converter.entity2model.Brand2Model;
import com.sav.autobase.webapp.converter.entity2model.ModelVehicle2Model;
import com.sav.autobase.webapp.converter.entity2model.Place2Model;
import com.sav.autobase.webapp.converter.entity2model.Request2Model;
import com.sav.autobase.webapp.converter.entity2model.Trip2Model;
import com.sav.autobase.webapp.converter.entity2model.Type2Model;
import com.sav.autobase.webapp.converter.entity2model.User2Model;
import com.sav.autobase.webapp.converter.entity2model.Vehicle2Model;
import com.sav.autobase.webapp.converter.model2entity.Model2Brand;
import com.sav.autobase.webapp.converter.model2entity.Model2ModelVehicle;
import com.sav.autobase.webapp.converter.model2entity.Model2Place;
import com.sav.autobase.webapp.converter.model2entity.Model2Request;
import com.sav.autobase.webapp.converter.model2entity.Model2Trip;
import com.sav.autobase.webapp.converter.model2entity.Model2Type;
import com.sav.autobase.webapp.converter.model2entity.Model2User;
import com.sav.autobase.webapp.converter.model2entity.Model2UserSearch;
import com.sav.autobase.webapp.converter.model2entity.Model2Vehicle;
import com.sav.autobase.webapp.converter.model2entity.Model2VehicleSearch;
import com.sav.autobase.webapp.models.BrandVehicleModel;
import com.sav.autobase.webapp.models.IdModel;
import com.sav.autobase.webapp.models.ModelVehicleModel;
import com.sav.autobase.webapp.models.PlaceModel;
import com.sav.autobase.webapp.models.RequestModel;
import com.sav.autobase.webapp.models.TripModel;
import com.sav.autobase.webapp.models.TypeVehicleModel;
import com.sav.autobase.webapp.models.UserSearchModel;
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
	public ResponseEntity<?> getByIdVehicle(@PathVariable(value = "id") Integer vehicleIdParam)
			throws ServiceException {

		Vehicle vehicle = adminService.getVehicle(vehicleIdParam);
		VehicleModel vehicleModel = new Vehicle2Model().convert(vehicle);

		return new ResponseEntity<VehicleModel>(vehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleModel>> getAllVehicle() {

		List<Vehicle> getAll;
		try {
			getAll = adminService.getAllVehicle();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<VehicleModel> vehicleModel = new ArrayList<>();
		for (Vehicle vehicle : getAll) {
			vehicleModel.add(new Vehicle2Model().convert(vehicle));
		}

		return new ResponseEntity<List<VehicleModel>>(vehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle/criteria", method = RequestMethod.POST)
	public ResponseEntity<?> findVehicleByCriteria(@RequestBody VehicleSearchModel criteriaModel)
			throws ServiceException {

		VehicleSerachCriteria criteria = new Model2VehicleSearch().convert(criteriaModel);
		List<Vehicle> allCriteria = adminService.findVehicleByCriteria(criteria);

		List<VehicleModel> convertCriteria = new ArrayList<>();
		for (Vehicle vehicle : allCriteria) {
			convertCriteria.add(new Vehicle2Model().convert(vehicle));
		}

		return new ResponseEntity<List<VehicleModel>>(convertCriteria, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.PUT)
	public ResponseEntity<?> saveVehicle(@RequestBody VehicleModel vehicleModel)
			throws ServiceException, ModifyException {

		Vehicle vehicle = new Model2Vehicle().convert(vehicleModel);
		adminService.saveVehicle(vehicle);

		return new ResponseEntity<IdModel>(new IdModel(vehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Integer vehicleIdParam) throws ServiceException {

		adminService.deleteVehicle(vehicleIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Model Vehicle---- */
	@RequestMapping(value = "/modelvehicle/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdModelVehicle(@PathVariable(value = "id") Integer modelVehicleIdParam)
			throws ServiceException {

		ModelVehicle modelVehicle = adminService.getModelVehicle(modelVehicleIdParam);
		ModelVehicleModel modelVehicleModel = new ModelVehicle2Model().convert(modelVehicle);

		return new ResponseEntity<ModelVehicleModel>(modelVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/modelvehicle", method = RequestMethod.GET)
	public ResponseEntity<List<ModelVehicleModel>> getAllModelVehicle() throws ServiceException {

		List<ModelVehicle> getAll = adminService.getAllModelVehicle();

		List<ModelVehicleModel> modelVehicleModel = new ArrayList<>();
		for (ModelVehicle modelVehicle : getAll) {
			modelVehicleModel.add(new ModelVehicle2Model().convert(modelVehicle));
		}

		return new ResponseEntity<List<ModelVehicleModel>>(modelVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/modelvehicle", method = RequestMethod.PUT)
	public ResponseEntity<?> saveModelVehicle(@RequestBody ModelVehicleModel modelVehicleModel)
			throws ServiceException, ModifyException {

		ModelVehicle modelVehicle = new Model2ModelVehicle().convert(modelVehicleModel);
		adminService.saveModel(modelVehicle);

		return new ResponseEntity<IdModel>(new IdModel(modelVehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/modelvehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteModelVehicle(@PathVariable(value = "id") Integer modelVehicleIdParam)
			throws ServiceException {

		adminService.deleteModel(modelVehicleIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Brand Vehicle---- */
	@RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdBrandVehicle(@PathVariable(value = "id") Integer brandIdParam)
			throws ServiceException {

		BrandVehicle brandVehicle = adminService.getBrand(brandIdParam);
		BrandVehicleModel brandVehicleModel = new Brand2Model().convert(brandVehicle);

		return new ResponseEntity<BrandVehicleModel>(brandVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/brand", method = RequestMethod.PUT)
	public ResponseEntity<?> saveBrandVehicle(@RequestBody BrandVehicleModel brandVehicleModel)
			throws ServiceException, ModifyException {

		BrandVehicle brandVehicle = new Model2Brand().convert(brandVehicleModel);
		adminService.addNewBrand(brandVehicle);

		return new ResponseEntity<IdModel>(new IdModel(brandVehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/brand/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBrandVehicle(@PathVariable(value = "id") Integer brandIdParam)
			throws ServiceException {

		adminService.deleteBrand(brandIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Type Vehicle---- */
	@RequestMapping(value = "/type/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTypeVehicle(@PathVariable(value = "id") Integer typeIdParam)
			throws ServiceException {

		TypeVehicle typeVehicle = adminService.getType(typeIdParam);
		TypeVehicleModel typeVehicleModel = new Type2Model().convert(typeVehicle);

		return new ResponseEntity<TypeVehicleModel>(typeVehicleModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/type", method = RequestMethod.PUT)
	public ResponseEntity<?> saveTypeVehicle(@RequestBody TypeVehicleModel typeVehicleModel)
			throws ServiceException, ModifyException {

		TypeVehicle typeVehicle = new Model2Type().convert(typeVehicleModel);
		adminService.addNewType(typeVehicle);

		return new ResponseEntity<IdModel>(new IdModel(typeVehicle.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTypeVehicle(@PathVariable(value = "id") Integer typeIdParam)
			throws ServiceException {

		adminService.deleteType(typeIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on User---- */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdUser(@PathVariable(value = "id") Integer userIdParam) throws ServiceException {

		Users user = adminService.getUser(userIdParam);
		UsersModel userModel = new User2Model().convert(user);

		return new ResponseEntity<UsersModel>(userModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UsersModel>> getAllUser() throws ServiceException {

		List<Users> getAll = adminService.getAllUser();

		List<UsersModel> userModel = new ArrayList<>();
		for (Users user : getAll) {
			userModel.add(new User2Model().convert(user));
		}

		return new ResponseEntity<List<UsersModel>>(userModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/criteria", method = RequestMethod.POST)
	public ResponseEntity<?> findUserByCriteria(@RequestBody UserSearchModel criteriaModel) throws ServiceException {

		UserSearchCriteria criteria = new Model2UserSearch().convert(criteriaModel);
		List<Users> allCriteria = adminService.findUserByCriteria(criteria);

		List<UsersModel> convertCriteria = new ArrayList<>();
		for (Users user : allCriteria) {
			convertCriteria.add(new User2Model().convert(user));
		}

		return new ResponseEntity<List<UsersModel>>(convertCriteria, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseEntity<?> saveUser(@RequestBody UsersModel userModel) throws ServiceException, ModifyException {

		Users user = new Model2User().convert(userModel);
		adminService.saveUser(user);

		return new ResponseEntity<IdModel>(new IdModel(user.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userIdParam) throws ServiceException {

		adminService.deleteUser(userIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Place---- */
	@RequestMapping(value = "/place/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdPlace(@PathVariable(value = "id") Integer placeIdParam) throws ServiceException {

		Place place = adminService.getPlace(placeIdParam);
		PlaceModel placeModel = new Place2Model().convert(place);

		return new ResponseEntity<PlaceModel>(placeModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public ResponseEntity<List<PlaceModel>> getAllPlace() throws ServiceException {

		List<Place> getAll = adminService.getAllPlace();

		List<PlaceModel> placeModel = new ArrayList<>();
		for (Place place : getAll) {
			placeModel.add(new Place2Model().convert(place));
		}

		return new ResponseEntity<List<PlaceModel>>(placeModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/place", method = RequestMethod.PUT)
	public ResponseEntity<?> savePlace(@RequestBody PlaceModel placeModel) throws ServiceException, ModifyException {

		Place place = new Model2Place().convert(placeModel);
		adminService.savePlace(place);

		return new ResponseEntity<IdModel>(new IdModel(place.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/place/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePlace(@PathVariable(value = "id") Integer placeIdParam) throws ServiceException {

		adminService.deletePlace(placeIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Trip---- */
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTrip(@PathVariable(value = "id") Integer tripIdParam) throws ServiceException {

		Trip trip = adminService.getTrip(tripIdParam);
		TripModel tripModel = new Trip2Model().convert(trip);

		return new ResponseEntity<TripModel>(tripModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip", method = RequestMethod.GET)
	public ResponseEntity<List<TripModel>> getAllTrip() throws ServiceException {

		List<Trip> getAll = adminService.getAllTrip();

		List<TripModel> tripModel = new ArrayList<>();
		for (Trip trip : getAll) {
			tripModel.add(new Trip2Model().convert(trip));
		}

		return new ResponseEntity<List<TripModel>>(tripModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/trip", method = RequestMethod.PUT)
	public ResponseEntity<?> saveTrip(@RequestBody TripModel tripModel) throws ServiceException, ModifyException {

		Trip trip = new Model2Trip().convert(tripModel);
		adminService.saveTrip(trip);

		return new ResponseEntity<IdModel>(new IdModel(trip.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrip(@PathVariable(value = "id") Integer tripIdParam) throws ServiceException {

		adminService.deleteTrip(tripIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Request---- */
	@RequestMapping(value = "/request/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdRequest(@PathVariable(value = "id") Integer requestIdParam)
			throws ServiceException {

		Request request = adminService.getRequest(requestIdParam);
		RequestModel requestModel = new Request2Model().convert(request);

		return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/requests", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllRequest() throws ServiceException {

		List<Request> getAll = adminService.getAllRequest();

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAll) {
			requestModel.add(new Request2Model().convert(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllByStatusRequest(@RequestParam(value = "status", required = true) StatusRequest status) throws ServiceException {

		List<Request> getAllByStatus = adminService.getAllByStatusRequest(status);

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAllByStatus) {
			requestModel.add(new Request2Model().convert(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/request/{users}", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllRequestByUser(@RequestBody UsersModel userModel)
			throws ServiceException {

		Users user = new Model2User().convert(userModel);
		List<Request> getAll = adminService.getAllRequestByUser(user);

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAll) {
			requestModel.add(new Request2Model().convert(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/request", method = RequestMethod.PUT)
	public ResponseEntity<?> saveRequest(@RequestBody RequestModel requestModel)
			throws ServiceException, ModifyException {

		Request reqeust = new Model2Request().convert(requestModel);
		adminService.saveRequest(reqeust);

		return new ResponseEntity<IdModel>(new IdModel(reqeust.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Integer reqeustIdParam) throws ServiceException {

		adminService.deleteRequest(reqeustIdParam);

		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

}

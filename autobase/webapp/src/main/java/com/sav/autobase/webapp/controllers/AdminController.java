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
import com.sav.autobase.datamodel.TypeUsers;
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
import com.sav.autobase.webapp.converter.model2entity.Model2Client;
import com.sav.autobase.webapp.converter.model2entity.Model2Driver;
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

/**
 * This class AdminController includes a set of methods of action necessary for
 * the user administrator
 * 
 * @author AlexStrug
 * @version 1
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Inject
	private IAdminService adminService;

	/* ----All methods on Vehicle---- */
	/**
	 * The method return by "ID" vehicle
	 * 
	 * @param vehicleIdParam
	 *            - transferring by "ID" for get by "ID" vehicle
	 * @return 200 HttpStatus.OK if successfully get by "ID" and get the vehicle
	 *         <br>
	 *         204 NO_CONTENT if error with get vehicle by "ID" <br>
	 */
	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdVehicle(@PathVariable(value = "id") Integer vehicleIdParam) {

		if (vehicleIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Vehicle vehicle;
		try {
			vehicle = adminService.getVehicle(vehicleIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (vehicle == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		VehicleModel vehicleModel = new Vehicle2Model().convert(vehicle);

		return new ResponseEntity<VehicleModel>(vehicleModel, HttpStatus.OK);
	}

	/**
	 * The method returns all vehicle
	 * 
	 * @return HttpStatus.OK if successfully get all and get all the vehicle
	 *         <br>
	 *         HttpStatus.NO_CONTENT if error with get all vehicle <br>
	 */
	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleModel>> getAllVehicle() {

		List<Vehicle> getAllVehicle;
		try {
			getAllVehicle = adminService.getAllVehicle();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (getAllVehicle == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<VehicleModel> vehicleModel = new ArrayList<>();
		for (Vehicle vehicle : getAllVehicle) {
			vehicleModel.add(new Vehicle2Model().convert(vehicle));
		}

		return new ResponseEntity<List<VehicleModel>>(vehicleModel, HttpStatus.OK);
	}

	/**
	 * The method return vehicle find by criteria
	 * 
	 * @param criteriaModel
	 *            - transferring the criteria for search vehicle from Database
	 * @return HttpStatus.OK if vehicle successfully find by criteria <br>
	 *         HttpStatus.NO_CONTENT if error find vehicle by the criteria
	 */
	@RequestMapping(value = "/vehicle/criteria", method = RequestMethod.POST)
	public ResponseEntity<?> findVehicleByCriteria(@RequestBody VehicleSearchModel criteriaModel) {

		if (criteriaModel == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		VehicleSerachCriteria criteria = new Model2VehicleSearch().convert(criteriaModel);

		criteariaApplyChanges(criteria, criteriaModel);

		List<Vehicle> allCriteria;
		try {
			allCriteria = adminService.findVehicleByCriteria(criteria);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<VehicleModel> convertCriteria = new ArrayList<>();
		for (Vehicle vehicle : allCriteria) {
			convertCriteria.add(new Vehicle2Model().convert(vehicle));
		}

		return new ResponseEntity<List<VehicleModel>>(convertCriteria, HttpStatus.OK);
	}

	/**
	 * The method is used in findVehicleByCriteria method and checks what
	 * parameters came to update
	 * 
	 * @param criteriaFromDb
	 *            - transferring the VehicleSerachCriteria for update trip from
	 *            Database
	 * @param criteriaModel
	 *            - transferring the VehicleSearchModel for update trip from
	 *            http request
	 */
	public void criteariaApplyChanges(VehicleSerachCriteria criteriaFromDb, VehicleSearchModel criteriaModel) {

		if (criteriaModel.getBrand() != null) {
			criteriaFromDb.setBrand(criteriaModel.getBrand());
		}
		if (criteriaModel.getType() != null) {
			criteriaFromDb.setType(criteriaModel.getType());
		}
		if (criteriaModel.getNameModel() != null) {
			criteriaFromDb.setNameModel(criteriaModel.getNameModel());
		}
		if (criteriaModel.getRegisterNumber() != null) {
			criteriaFromDb.setRegisterNumber(criteriaModel.getRegisterNumber());
		}
		if (criteriaModel.getCountOfPassenger() != null) {
			criteriaFromDb.setCountOfPassenger(criteriaModel.getCountOfPassenger());
		}
		if (criteriaModel.getReadyCrashCar() != null) {
			criteriaFromDb.setReadyCrashCar(criteriaModel.getReadyCrashCar());
		}
	}

	/**
	 * The method returns the trip "ID" who successfully passed created or
	 * updated
	 * 
	 * @param vehicleModel
	 *            - transferring the new vehicle for saveVehicle
	 * @return HttpStatus.CREATED if vehicle successfully created or updated and
	 *         "ID" the vehicle <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new vehicle
	 */
	@RequestMapping(value = "/vehicle", method = RequestMethod.PUT)
	public ResponseEntity<?> saveVehicle(@RequestBody VehicleModel vehicleModel) {

		if (vehicleModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Vehicle vehicle = new Model2Vehicle().convert(vehicleModel);
		if (vehicle.getId() != null) {
			vehicleApplyChanges(vehicle, vehicleModel);
		}
		try {
			adminService.saveVehicle(vehicle);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IdModel>(new IdModel(vehicle.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method is used in updateVehicle method and checks what parameters
	 * came to update
	 * 
	 * @param vehicleFromDb
	 *            - transferring the Vehicle for update vehicle from Database
	 * @param vehicleModel
	 *            - transferring the VehicleModel for update vehicle from http
	 *            request
	 */
	public void vehicleApplyChanges(Vehicle vehicleFromDb, VehicleModel vehicleModel) {

		if (vehicleModel.getModel() != null) {
			vehicleFromDb.setModel(new Model2ModelVehicle().convert(vehicleModel.getModel()));
		}
		if (vehicleModel.getDriver() != null) {
			vehicleFromDb.setDriver(new Model2Driver().convert(vehicleModel.getDriver()));
		}
		if (vehicleModel.isReadyCrashCar() != null) {
			vehicleFromDb.setReadyCrashCar(vehicleModel.isReadyCrashCar());
		}

	}

	/**
	 * The method returns HttpStatus if trip successfully deleted
	 * 
	 * @param vehicleIdParam
	 *            - transferring by "ID" for delete by "ID" vehicle
	 * @return HttpStatus.OK if vehicle successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted vehicle
	 */
	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Integer vehicleIdParam) {

		if (vehicleIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		try {
			adminService.deleteVehicle(vehicleIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Model Vehicle---- */
	/**
	 * The method return by "ID" modelVehicle
	 * 
	 * @param modelVehicleIdParam
	 *            - transferring by "ID" for get by "ID" request
	 * @return HttpStatus.OK if successfully get by "ID" and get the
	 *         modelVehicleIdParam <br>
	 *         HttpStatus.NO_CONTENT if error with get modelVehicleIdParam by
	 *         "ID" <br>
	 */
	@RequestMapping(value = "/modelvehicle/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdModelVehicle(@PathVariable(value = "id") Integer modelVehicleIdParam) {

		if (modelVehicleIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		ModelVehicle modelVehicle;
		try {
			modelVehicle = adminService.getModelVehicle(modelVehicleIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (modelVehicle == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		ModelVehicleModel modelVehicleModel = new ModelVehicle2Model().convert(modelVehicle);

		return new ResponseEntity<ModelVehicleModel>(modelVehicleModel, HttpStatus.OK);
	}

	/**
	 * The method return all modelVehicle
	 * 
	 * @return HttpStatus.OK if successfully get all and get all the
	 *         modelVehicles <br>
	 *         HttpStatus.NO_CONTENT if error with get all modelVehicles <br>
	 */
	@RequestMapping(value = "/modelvehicle", method = RequestMethod.GET)
	public ResponseEntity<List<ModelVehicleModel>> getAllModelVehicle() {

		List<ModelVehicle> getAllModelVehicle;
		try {
			getAllModelVehicle = adminService.getAllModelVehicle();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllModelVehicle == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<ModelVehicleModel> modelVehicleModel = new ArrayList<>();
		for (ModelVehicle modelVehicle : getAllModelVehicle) {
			modelVehicleModel.add(new ModelVehicle2Model().convert(modelVehicle));
		}

		return new ResponseEntity<List<ModelVehicleModel>>(modelVehicleModel, HttpStatus.OK);
	}

	/**
	 * The method returns the modelVehicle "ID" who successfully passed created
	 * or updated
	 * 
	 * @param modelVehicleModel
	 *            - transferring the new modelVehicle for saveModelVehicle
	 * @return HttpStatus.CREATED if modelVehicle successfully created or
	 *         updated and "ID" the modelVehicle <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new
	 *         modelVehicle
	 */
	@RequestMapping(value = "/modelvehicle", method = RequestMethod.PUT)
	public ResponseEntity<?> saveModelVehicle(@RequestBody ModelVehicleModel modelVehicleModel) {

		if (modelVehicleModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		ModelVehicle modelVehicle = new Model2ModelVehicle().convert(modelVehicleModel);
		if (modelVehicle.getId() != null) {
			modelVehicleApplyChanges(modelVehicle, modelVehicleModel);
		}
		try {
			adminService.saveModel(modelVehicle);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<IdModel>(new IdModel(modelVehicle.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method is used in updateModelVehicle method and checks what
	 * parameters came to update
	 * 
	 * @param modelVehicleFromDb
	 *            - transferring the ModelVehicle for update modelVehicle from
	 *            Database
	 * @param modelVehicleModel
	 *            - transferring the ModelVehicleModel for update modelVehicle
	 *            from http request
	 */
	public void modelVehicleApplyChanges(ModelVehicle modelVehicleFromDb, ModelVehicleModel modelVehicleModel) {

		if (modelVehicleModel.getBrand() != null) {
			modelVehicleFromDb.setBrand(new Model2Brand().convert(modelVehicleModel.getBrand()));
		}
		if (modelVehicleModel.getNameModel() != null) {
			modelVehicleFromDb.setNameModel(modelVehicleModel.getNameModel());
		}
		if (modelVehicleModel.getRegisterNumber() != null) {
			modelVehicleFromDb.setRegisterNumber(modelVehicleModel.getRegisterNumber());
		}
		if (modelVehicleModel.getType() != null) {
			modelVehicleFromDb.setType(new Model2Type().convert(modelVehicleModel.getType()));
		}
		if (modelVehicleModel.getCountOfPassenger() != null) {
			modelVehicleFromDb.setCountOfPassenger(modelVehicleModel.getCountOfPassenger());
		}
	}

	/**
	 * The method returns HttpStatus if modelVehicle successfully deleted
	 * 
	 * @param modelVehicleIdParam
	 *            - transferring by "ID" for delete by "ID" modelVehicle
	 * @return HttpStatus.OK if modelVehicle successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted modelVehicle
	 */
	@RequestMapping(value = "/modelvehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteModelVehicle(@PathVariable(value = "id") Integer modelVehicleIdParam) {

		if (modelVehicleIdParam == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			adminService.deleteModel(modelVehicleIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Brand Vehicle---- */
	/**
	 * The method return by "ID" brandVehicle
	 * 
	 * @param brandIdParam
	 *            - transferring by "ID" for get by "ID" brandVehicle
	 * @return HttpStatus.OK if successfully get by "ID" and get the
	 *         brandVehicle <br>
	 *         HttpStatus.NO_CONTENT if error with get brandVehicle by "ID" <br>
	 */
	@RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdBrandVehicle(@PathVariable(value = "id") Integer brandIdParam) {

		if (brandIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		BrandVehicle brandVehicle;
		try {
			brandVehicle = adminService.getBrand(brandIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (brandVehicle == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		BrandVehicleModel brandVehicleModel = new Brand2Model().convert(brandVehicle);

		return new ResponseEntity<BrandVehicleModel>(brandVehicleModel, HttpStatus.OK);
	}

	/**
	 * The method returns the brandVehicle "ID" who successfully passed created
	 * or updated
	 * 
	 * @param brandVehicleModel
	 *            - transferring the new brandVehicle for saveBrandVehicle
	 * @return HttpStatus.CREATED if brandVehicle successfully created or
	 *         updated and "ID" the brandVehicle <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new
	 *         brandVehiclea
	 */
	@RequestMapping(value = "/brand", method = RequestMethod.PUT)
	public ResponseEntity<?> saveBrandVehicle(@RequestBody BrandVehicleModel brandVehicleModel) {

		if (brandVehicleModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		BrandVehicle brandVehicle = new Model2Brand().convert(brandVehicleModel);
		try {
			adminService.addNewBrand(brandVehicle);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<IdModel>(new IdModel(brandVehicle.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method returns HttpStatus if brandVehicle successfully deleted
	 * 
	 * @param brandIdParam
	 *            - transferring by "ID" for delete by "ID" brandVehicle
	 * @return HttpStatus.OK if brandVehicle successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted brandVehicle
	 */
	@RequestMapping(value = "/brand/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBrandVehicle(@PathVariable(value = "id") Integer brandIdParam) {

		if (brandIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		try {
			adminService.deleteBrand(brandIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Type Vehicle---- */
	/**
	 * The method return by "ID" typeVehicle
	 * 
	 * @param typeIdParam
	 *            - transferring by "ID" for get by "ID" typeVehicle
	 * @return HttpStatus.OK if successfully get by "ID" and get the typeVehicle
	 *         <br>
	 *         HttpStatus.NO_CONTENT if error with get typeVehicle by "ID" <br>
	 */
	@RequestMapping(value = "/type/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTypeVehicle(@PathVariable(value = "id") Integer typeIdParam) {

		if (typeIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		TypeVehicle typeVehicle;
		try {
			typeVehicle = adminService.getType(typeIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (typeVehicle == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		TypeVehicleModel typeVehicleModel = new Type2Model().convert(typeVehicle);

		return new ResponseEntity<TypeVehicleModel>(typeVehicleModel, HttpStatus.OK);
	}

	/**
	 * The method returns the typeVehicle "ID" who successfully passed created
	 * or updated
	 * 
	 * @param typeVehicleModel
	 *            - transferring the new typeVehicle for saveTypeVehicle
	 * @return HttpStatus.CREATED if typeVehicle successfully created or updated
	 *         and "ID" the typeVehicle <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new
	 *         typeVehicle
	 */
	@RequestMapping(value = "/type", method = RequestMethod.PUT)
	public ResponseEntity<?> saveTypeVehicle(@RequestBody TypeVehicleModel typeVehicleModel) {

		if (typeVehicleModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		TypeVehicle typeVehicle = new Model2Type().convert(typeVehicleModel);
		try {
			adminService.addNewType(typeVehicle);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<IdModel>(new IdModel(typeVehicle.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method returns HttpStatus if typeVehicle successfully deleted
	 * 
	 * @param typeIdParam
	 *            - transferring by "ID" for delete by "ID" typeVehicle
	 * @return HttpStatus.OK if typeVehicle successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted typeVehicle
	 */
	@RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTypeVehicle(@PathVariable(value = "id") Integer typeIdParam) {

		if (typeIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		try {
			adminService.deleteType(typeIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on User---- */
	/**
	 * The method return by "ID" user
	 * 
	 * @param userIdParam
	 *            - transferring by "ID" for get by "ID" user
	 * @return HttpStatus.OK if successfully get by "ID" and get the user <br>
	 *         HttpStatus.NO_CONTENT if error with get user by "ID" <br>
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdUser(@PathVariable(value = "id") Integer userIdParam) {

		if (userIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Users user;
		try {
			user = adminService.getUser(userIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		UsersModel userModel = new User2Model().convert(user);

		return new ResponseEntity<UsersModel>(userModel, HttpStatus.OK);
	}

	/**
	 * The method returns all user
	 * 
	 * @return HttpStatus.OK if successfully get all and get all the users <br>
	 *         HttpStatus.NO_CONTENT if error with get all users <br>
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UsersModel>> getAllUser() {

		List<Users> getAllUser;
		try {
			getAllUser = adminService.getAllUser();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllUser == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<UsersModel> userModel = new ArrayList<>();
		for (Users user : getAllUser) {
			userModel.add(new User2Model().convert(user));
		}

		return new ResponseEntity<List<UsersModel>>(userModel, HttpStatus.OK);
	}

	/**
	 * The method return user find by criteria
	 * 
	 * @param criteriaModel
	 *            - transferring the criteria for search user from Database
	 * @return HttpStatus.OK if user successfully find by criteria <br>
	 *         HttpStatus.NO_CONTENT if error find user by criteria
	 */
	@RequestMapping(value = "/users/criteria", method = RequestMethod.POST)
	public ResponseEntity<?> findUserByCriteria(@RequestBody UserSearchModel criteriaModel) {

		if (criteriaModel == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		UserSearchCriteria criteria = new Model2UserSearch().convert(criteriaModel);
		List<Users> allCriteria;
		try {
			allCriteria = adminService.findUserByCriteria(criteria);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (allCriteria == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<UsersModel> convertCriteria = new ArrayList<>();
		for (Users user : allCriteria) {
			convertCriteria.add(new User2Model().convert(user));
		}

		return new ResponseEntity<List<UsersModel>>(convertCriteria, HttpStatus.OK);
	}

	/**
	 * The method returns the user "ID" who successfully passed created or
	 * updated
	 * 
	 * @param userModel
	 *            - transferring the new user for saveUser
	 * @return HttpStatus.CREATED if user successfully created or updated and
	 *         "ID" the user <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new user
	 */
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseEntity<?> saveUser(@RequestBody UsersModel userModel) {

		if (userModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Users user = new Model2User().convert(userModel);
		if (user.getId() != null) {
			userApplyChanges(user, userModel);
		}
		try {
			adminService.saveUser(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<IdModel>(new IdModel(user.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method is used in updateUser method and checks what parameters came
	 * to update
	 * 
	 * @param userFromDb
	 *            - transferring the Users for update user from Database
	 * @param userModel
	 *            - transferring the UsersModel for update user from http
	 *            request
	 */
	public void userApplyChanges(Users userFromDb, UsersModel userModel) {

		if (userModel.getFirstName() != null) {
			userFromDb.setFirstName(userModel.getFirstName());
		}
		if (userModel.getLastName() != null) {
			userFromDb.setLastName(userModel.getLastName());
		}
		if (userModel.getDateBirth() != null) {
			userFromDb.setDateBirth(userModel.getDateBirth());
		}
		if (userModel.getLogin() != null) {
			userFromDb.setLogin(userModel.getLogin());
		}
		if (userModel.getPassword() != null) {
			userFromDb.setPassword(userModel.getPassword());
		}
		if (userModel.getEmail() != null) {
			userFromDb.setEmail(userModel.getPassword());
		}
		if (userModel.getType() != null) {
			userFromDb.setType(TypeUsers.valueOf(userModel.getType()));
		}
	}

	/**
	 * The method returns HttpStatus if user successfully deleted
	 * 
	 * @param userIdParam
	 *            - transferring by "ID" for delete by "ID" user
	 * @return HttpStatus.OK if user successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted user
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userIdParam) {

		if (userIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		try {
			adminService.deleteUser(userIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Place---- */
	/**
	 * The method return by "ID" place
	 * 
	 * @param placeIdParam
	 *            - transferring by "ID" for get by "ID" place
	 * @return HttpStatus.OK if successfully get by "ID" and get the place <br>
	 *         HttpStatus.NO_CONTENT if error with get place by "ID" <br>
	 */
	@RequestMapping(value = "/place/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdPlace(@PathVariable(value = "id") Integer placeIdParam) {

		if (placeIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Place place;
		try {
			place = adminService.getPlace(placeIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (place == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		PlaceModel placeModel = new Place2Model().convert(place);

		return new ResponseEntity<PlaceModel>(placeModel, HttpStatus.OK);
	}

	/**
	 * The method return all places
	 * 
	 * @return HttpStatus.OK if successfully get all and get all the places <br>
	 *         HttpStatus.NO_CONTENT if error with get all places <br>
	 */
	@RequestMapping(value = "/place", method = RequestMethod.GET)
	public ResponseEntity<List<PlaceModel>> getAllPlace() {

		List<Place> getAllPlace;
		try {
			getAllPlace = adminService.getAllPlace();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllPlace == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<PlaceModel> placeModel = new ArrayList<>();
		for (Place place : getAllPlace) {
			placeModel.add(new Place2Model().convert(place));
		}

		return new ResponseEntity<List<PlaceModel>>(placeModel, HttpStatus.OK);
	}

	/**
	 * The method returns the place "ID" who successfully passed created or
	 * updated
	 * 
	 * @param placeModel
	 *            - transferring the new place for savePlace
	 * @return HttpStatus.CREATED if place successfully created or updated and
	 *         "ID" the place <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new place
	 */
	@RequestMapping(value = "/place", method = RequestMethod.PUT)
	public ResponseEntity<?> savePlace(@RequestBody PlaceModel placeModel) {

		if (placeModel == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Place place = new Model2Place().convert(placeModel);
		if (place.getId() != null) {
			placeApplyChanges(place, placeModel);
		}
		try {
			adminService.savePlace(place);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(new IdModel(place.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method is used in updatePlace method and checks what parameters came
	 * to update
	 * 
	 * @param placeFromDb
	 *            - transferring the Place for update place from Database
	 * @param placeModel
	 *            - transferring the PlaceModel for update place from http
	 *            request
	 */
	public void placeApplyChanges(Place placeFromDb, PlaceModel placeModel) {

		if (placeModel.getPlaceStart() != null) {
			placeFromDb.setPlaceStart(placeModel.getPlaceStart());
		}
		if (placeModel.getPlaceEnd() != null) {
			placeFromDb.setPlaceEnd(placeModel.getPlaceEnd());
		}
		if (placeModel.getDistance() != null) {
			placeFromDb.setDistance(placeModel.getDistance());
		}
	}

	/**
	 * The method returns HttpStatus if place successfully deletedF
	 * 
	 * @param placeIdParam
	 *            - transferring by "ID" for delete by "ID" place
	 * @return HttpStatus.OK if place successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted place
	 */
	@RequestMapping(value = "/place/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePlace(@PathVariable(value = "id") Integer placeIdParam) {

		if (placeIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		try {
			adminService.deletePlace(placeIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Trip---- */
	/**
	 * The method return by "ID" trip
	 * 
	 * @param tripIdParam
	 *            - transferring by "ID" for get by "ID" trip
	 * @return HttpStatus.OK if successfully get by "ID" and get the trip <br>
	 *         HttpStatus.NO_CONTENT if error with get trip by "ID" <br>
	 */
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdTrip(@PathVariable(value = "id") Integer tripIdParam) {

		if (tripIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Trip trip;
		try {
			trip = adminService.getTrip(tripIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (trip == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		TripModel tripModel = new Trip2Model().convert(trip);

		return new ResponseEntity<TripModel>(tripModel, HttpStatus.OK);
	}

	/**
	 * The method returns all trips
	 * 
	 * @return HttpStatus.OK if successfully get all and get all the trips <br>
	 *         HttpStatus.NO_CONTENT if error with get all trips <br>
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.GET)
	public ResponseEntity<List<TripModel>> getAllTrip() {

		List<Trip> getAllTrip;
		try {
			getAllTrip = adminService.getAllTrip();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllTrip == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<TripModel> tripModel = new ArrayList<>();
		for (Trip trip : getAllTrip) {
			tripModel.add(new Trip2Model().convert(trip));
		}

		return new ResponseEntity<List<TripModel>>(tripModel, HttpStatus.OK);
	}

	/**
	 * The method returns the trip "ID" who successfully passed created or
	 * updated
	 * 
	 * @param tripModel
	 *            - transferring the new trip for saveTrip
	 * @return HttpStatus.CREATED if trip successfully created or updated and
	 *         "ID" the trip <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new trip
	 */
	@RequestMapping(value = "/trip", method = RequestMethod.PUT)
	public ResponseEntity<?> saveTrip(@RequestBody TripModel tripModel) {

		if (tripModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Trip trip = new Model2Trip().convert(tripModel);
		if (trip.getId() != null)
			try {
				adminService.saveTrip(trip);
			} catch (ServiceException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		return new ResponseEntity<IdModel>(new IdModel(trip.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method is used in updateRequest method and checks what parameters
	 * came to update
	 * 
	 * @param tripFromDb
	 *            - transferring the Trip for update trip from Database
	 * @param tripModel
	 *            - transferring the TripModel for update trip from http request
	 */
	public void tripApplyChanges(Trip tripFromDb, TripModel tripModel) {

		if (tripModel.getRequest() != null) {
			tripFromDb.setRequest(new Model2Request().convert(tripModel.getRequest()));
		}
		if (tripModel.getVehicle() != null) {
			tripFromDb.setVehicle(new Model2Vehicle().convert(tripModel.getVehicle()));
		}
		if (tripModel.isEndTrip() != null) {
			tripFromDb.setEndTrip(tripModel.isEndTrip());
		}
	}

	/**
	 * The method returns HttpStatus if trip successfully deleted
	 * 
	 * @param tripIdParam
	 *            - transferring by "ID" for delete by "ID" trip
	 * @return HttpStatus.OK if trip successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted trip
	 */
	@RequestMapping(value = "/trip/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrip(@PathVariable(value = "id") Integer tripIdParam) {

		if (tripIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		try {
			adminService.deleteTrip(tripIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	/* ----All methods on Request---- */
	/**
	 * The method return by "ID" request
	 * 
	 * @param requestIdParam
	 *            - transferring by "ID" for get by "ID" request
	 * @return HttpStatus.OK if successfully get by "ID" and get the request
	 *         <br>
	 *         HttpStatus.NO_CONTENT if error with get request by "ID" <br>
	 */
	@RequestMapping(value = "/requestGet/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByIdRequest(@PathVariable(value = "id") Integer requestIdParam) {

		if (requestIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Request request;
		try {
			request = adminService.getRequest(requestIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (request == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		RequestModel requestModel = new Request2Model().convert(request);

		return new ResponseEntity<RequestModel>(requestModel, HttpStatus.OK);
	}

	/**
	 * The method return all requests
	 * 
	 * @return HttpStatus.OK if successfully get all and get all the requests
	 *         <br>
	 *         HttpStatus.NO_CONTENT if error with get all requestsF <br>
	 */
	@RequestMapping(value = "/requests", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllRequest() {

		List<Request> getAllRequest;
		try {
			getAllRequest = adminService.getAllRequest();
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllRequest == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAllRequest) {
			requestModel.add(new Request2Model().convert(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}

	/**
	 * The method return all requests by the status
	 * 
	 * @param status
	 *            - transferring by "status" for get all by status requests
	 * @return HttpStatus.OK if successfully get all by "status" and get all the
	 *         requests <br>
	 *         HttpStatus.NO_CONTENT if error with get all requests by "status"
	 *         <br>
	 */
	@RequestMapping(value = "/statusRequest", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllByStatusRequest(
			@RequestParam(value = "status", required = true) StatusRequest status) {

		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<Request> getAllByStatus;
		try {
			getAllByStatus = adminService.getAllByStatusRequest(status);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllByStatus == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAllByStatus) {
			requestModel.add(new Request2Model().convert(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}

	/**
	 * The method returns all requests by "user"
	 * 
	 * @param userModel
	 *            - transferring by "user" for get all by user requests
	 * @return HttpStatus.OK if successfully get all by "user" and get all the
	 *         requests <br>
	 *         HttpStatus.NO_CONTENT if error with get all requests by "user"
	 *         <br>
	 */
	@RequestMapping(value = "/request/{users}", method = RequestMethod.GET)
	public ResponseEntity<List<RequestModel>> getAllRequestByUser(@RequestBody UsersModel userModel) {

		if (userModel == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Users user = new Model2User().convert(userModel);

		List<Request> getAllRequest;
		try {
			getAllRequest = adminService.getAllRequestByUser(user);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (getAllRequest == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<RequestModel> requestModel = new ArrayList<>();
		for (Request request : getAllRequest) {
			requestModel.add(new Request2Model().convert(request));
		}

		return new ResponseEntity<List<RequestModel>>(requestModel, HttpStatus.OK);
	}

	/**
	 * The method returns the request "ID" who successfully passed created or
	 * updated
	 * 
	 * @param requestModel
	 *            - transferring the new request for saveRequest
	 * @return HttpStatus.CREATED if trip successfully created or updated and
	 *         "ID" the request <br>
	 *         HttpStatus.BAD_REQUEST if error created or updated new request
	 */
	@RequestMapping(value = "/request", method = RequestMethod.PUT)
	public ResponseEntity<?> saveRequest(@RequestBody RequestModel requestModel)
			throws ServiceException, ModifyException {

		if (requestModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Request request = new Model2Request().convert(requestModel);
		try {
			adminService.saveRequest(request);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (request == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IdModel>(new IdModel(request.getId()), HttpStatus.CREATED);
	}

	/**
	 * The method is used in updateRequest method and checks what parameters
	 * came to update
	 * 
	 * @param requestFromDb
	 *            - transferring the Request for update request from Database
	 * @param requestModel
	 *            transferring the RequestModel for update request from http
	 *            request
	 */
	public void requestApplyChanges(Request requestFromDb, RequestModel requestModel) {

		if (requestModel.getClient() != null) {
			requestFromDb.setClient(new Model2Client().convert(requestModel.getClient()));
		}
		if (requestModel.getStartDate() != null) {
			requestFromDb.setStartDate(requestModel.getStartDate());
		}
		if (requestModel.getEndDate() != null) {
			requestFromDb.setEndDate(requestModel.getEndDate());
		}
		if (requestModel.getPlace() != null) {
			requestFromDb.setPlace(new Model2Place().convert(requestModel.getPlace()));
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

	/**
	 * The method returns HttpStatus if request successfully deleted
	 * 
	 * @param reqeustIdParam
	 *            - transferring by "ID" for delete by "ID" request
	 * @return HttpStatus.OK if request successfully deleted <br>
	 *         HttpStatus.NO_CONTENT if error deleted request
	 */
	@RequestMapping(value = "/request/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "id") Integer reqeustIdParam) throws ServiceException {

		if (reqeustIdParam == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		try {
			adminService.deleteRequest(reqeustIdParam);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

}

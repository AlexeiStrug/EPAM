package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

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
import com.sav.autobase.services.exception.ServiceException;

public interface IAdminService {

	Vehicle getVehicle(Integer id) throws ServiceException;

	List<Vehicle> getAllVehicle() throws ServiceException;

	List<Vehicle> findVehicleByCriteria(VehicleSerachCriteria criteria) throws ServiceException;

	@Transactional
	void saveVehicle(Vehicle vehicle) throws ServiceException;

	@Transactional
	void deleteVehicle(Integer id) throws ServiceException;

	ModelVehicle getModelVehicle(Integer id) throws ServiceException;

	List<ModelVehicle> getAllModelVehicle() throws ServiceException;

	@Transactional
	void saveModel(ModelVehicle model) throws ServiceException;

	@Transactional
	void deleteModel(Integer id) throws ServiceException;

	BrandVehicle getBrand(Integer id) throws ServiceException;

	@Transactional
	void addNewBrand(BrandVehicle brand) throws ServiceException;

	@Transactional
	void deleteBrand(Integer id) throws ServiceException;

	TypeVehicle getType(Integer id) throws ServiceException;

	@Transactional
	void addNewType(TypeVehicle type) throws ServiceException;

	@Transactional
	void deleteType(Integer id) throws ServiceException;

	Users getUser(Integer id) throws ServiceException;

	List<Users> getAllUser() throws ServiceException;

	List<Users> findUserByCriteria(UserSearchCriteria criteria) throws ServiceException;

	@Transactional
	void saveUser(Users user) throws ServiceException;

	@Transactional
	void deleteUser(Integer id) throws ServiceException;

	Place getPlace(Integer id) throws ServiceException;

	List<Place> getAllPlace() throws ServiceException;

	@Transactional
	void savePlace(Place place) throws ServiceException;

	@Transactional
	void deletePlace(Integer id) throws ServiceException;

	Trip getTrip(Integer id) throws ServiceException;

	List<Trip> getAllTrip() throws ServiceException;

	@Transactional
	void saveTrip(Trip trip) throws ServiceException;

	@Transactional
	void deleteTrip(Integer id) throws ServiceException;

	Request getRequest(Integer id) throws ServiceException;

	List<Request> getAllRequest() throws ServiceException;

	List<Request> getAllByStatusRequest(StatusRequest status) throws ServiceException;

	List<Request> getAllRequestByUser(Users user) throws ServiceException;

	@Transactional
	void saveRequest(Request request) throws ServiceException;

	@Transactional
	void deleteRequest(Integer id) throws ServiceException;

	/* ---Only for test--- */
	@Transactional
	void deleteAll() throws ServiceException;

}

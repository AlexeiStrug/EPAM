package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.DAOException;

public interface IAdminService {

	Vehicle getVehicle(Integer id) throws DAOException;

	List<Vehicle> getAllVehicle() throws DAOException;

	@Transactional
	void saveVehicle(Vehicle vehicle) throws DAOException;

	@Transactional
	void deleteVehicle(Integer id) throws DAOException;

	ModelVehicle getModelVehicle(Integer id) throws DAOException;

	List<ModelVehicle> getAllModelVehicle() throws DAOException;

	@Transactional
	void saveModel(ModelVehicle model) throws DAOException;

	@Transactional
	void deleteModel(Integer id) throws DAOException;

	BrandVehicle getBrand(Integer id) throws DAOException;

	@Transactional
	void addNewBrand(BrandVehicle brand) throws DAOException;

	@Transactional
	void deleteBrand(Integer id) throws DAOException;

	TypeVehicle getType(Integer id) throws DAOException;

	@Transactional
	void addNewType(TypeVehicle type) throws DAOException;

	@Transactional
	void deleteType(Integer id) throws DAOException;

	Users getUser(Integer id) throws DAOException;

	List<Users> getAllUser() throws DAOException;

	@Transactional
	void saveUser(Users user) throws DAOException;

	@Transactional
	void deleteUser(Integer id) throws DAOException;

	Place getPlace(Integer id) throws DAOException;

	List<Place> getAllPlace() throws DAOException;
	
	@Transactional
	void savePlace(Place place) throws DAOException;

	@Transactional
	void deletePlace(Integer id) throws DAOException;

	Trip getTrip(Integer id) throws DAOException;

	List<Trip> getAllTrip() throws DAOException;
	
	@Transactional 
	void saveTrip(Trip trip) throws DAOException;
	
	@Transactional
	void deleteTrip(Integer id) throws DAOException;

	Request getRequest(Integer id) throws DAOException;
	
	List<Request> getAllRequest() throws DAOException;
	
	List<Request> getAllRequestByUser(Users user) throws DAOException;
	
	@Transactional
	void saveRequest(Request request) throws DAOException;
	
	@Transactional
	void deleteRequest(Integer id) throws DAOException;
	
	/* ---Only for test--- */
	@Transactional
	void deleteAll() throws DAOException;

}

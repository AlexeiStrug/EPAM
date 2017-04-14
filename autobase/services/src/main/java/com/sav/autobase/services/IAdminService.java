package com.sav.autobase.services;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.BrandVehicle;
import com.sav.autobase.datamodel.ModelVehicle;
import com.sav.autobase.datamodel.Place;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.TypeVehicle;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.DAOException;

public interface IAdminService {

	@Transactional
	void saveVehicle(Vehicle vehicle) throws DAOException;

	@Transactional
	void deleteVehicle(Integer id) throws DAOException;

	@Transactional
	void saveModel(ModelVehicle model) throws DAOException;

	@Transactional
	void deleteModel(Integer id) throws DAOException;

	@Transactional
	void addNewBrand(BrandVehicle brand) throws DAOException;

	@Transactional
	void deleteBrand(Integer id) throws DAOException;

	@Transactional
	void addNewType(TypeVehicle type) throws DAOException;

	@Transactional
	void deleteType(Integer id) throws DAOException;

	@Transactional
	void saveUser(Users user) throws DAOException;

	@Transactional
	void deleteUser(Integer id) throws DAOException;

	@Transactional
	void savePlace(Place place) throws DAOException;

	@Transactional
	void deletePlace(Integer id) throws DAOException;

	@Transactional
	void deleteTrip(Integer id) throws DAOException;

	@Transactional
	void deleteRequest(Integer id) throws DAOException;

}

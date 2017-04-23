package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.DAOException;

public interface IDispatcherService {
	
	Request getRequestByStatus(Users user) throws DAOException;
	
	Request getRequest(Integer id) throws DAOException;

	Trip getTrip(Integer id) throws DAOException;

	List<Trip> getAllTrip() throws DAOException;

	List<Vehicle> findByCriteria(VehicleSerachCriteria criteria) throws DAOException;

	@Transactional
	void modifyRequest(Request request) throws DAOException;

	@Transactional
	void deleteTrip(Integer id) throws DAOException;

	@Transactional
	Trip createTrip(Request request, Vehicle vehicle) throws DAOException;

	@Transactional
	void modifyTrip(Trip trip) throws DAOException;

	Request findByStatus(StatusRequest status) throws DAOException;

}

package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.ServiceException;

public interface IDispatcherService {
	
	Request getRequestByStatus(Users user) throws ServiceException;
	
	Request getRequest(Integer id) throws ServiceException;
	
	Trip getTrip(Integer id) throws ServiceException;

	List<Trip> getAllTrip() throws ServiceException;

	List<Vehicle> findVehicleByCriteria(VehicleSerachCriteria criteria) throws ServiceException;

	@Transactional
	void modifyRequest(Request request) throws ServiceException;

	@Transactional
	void deleteTrip(Integer id) throws ServiceException;

	@Transactional
	Trip createTrip(Request request, Vehicle vehicle) throws ServiceException;

	@Transactional
	void modifyTrip(Trip trip) throws ServiceException;

	Request findByStatus(StatusRequest status) throws ServiceException;

}

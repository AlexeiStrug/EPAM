package com.sav.autobase.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.services.exception.DAOException;

public interface IDispatcherService {
	
	Request getRequest(Integer id) throws DAOException;

	List<Request> getAllRequest() throws DAOException;
	
	Request findByProcessed(String processed) throws DAOException;
	
	Trip getTrip(Integer id) throws DAOException;

	List<Trip> getAllTrip() throws DAOException;
	
	@Transactional
	void modifyRequest(Request request) throws DAOException;

	@Transactional
	void deleteRequest(Integer id) throws DAOException;
	
	@Transactional
	void createTrip(Trip trip) throws DAOException;
	
	@Transactional
	void modifyTrip(Trip trip) throws DAOException;
	
	@Transactional
	void deleteTrip(Integer id) throws DAOException;
	
}

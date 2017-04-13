package com.sav.autobase.services;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.DAOException;

public interface IDriverService {
	
	Trip getTrip(Integer id) throws DAOException;
	
	@Transactional
	Trip changeStatusTrip(Trip trip) throws DAOException;
	
	@Transactional
	Vehicle changeStatusVehicle(Vehicle vehicle) throws DAOException;

}

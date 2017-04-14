package com.sav.autobase.services;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.exception.DAOException;

public interface IDriverService {
	
	@Transactional
	void changeStatusTrip(Users user) throws DAOException;
	
	@Transactional
	void changeStatusVehicle(Users user) throws DAOException;

	Trip getTrip(Users user) throws DAOException;

}

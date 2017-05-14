package com.sav.autobase.services;

import org.springframework.transaction.annotation.Transactional;

import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.exception.ServiceException;

public interface IDriverService {
	
	@Transactional
	void changeStatusTrip(Users user) throws ServiceException;
	
	@Transactional
	void changeStatusVehicle(Users user) throws ServiceException;

	Trip getTrip(Users user) throws ServiceException;
	
	Vehicle getVehicle(Users user) throws ServiceException;

}

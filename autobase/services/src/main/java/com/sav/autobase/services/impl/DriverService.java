package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.api.ITripDao;
import com.sav.autobase.dao.api.IVehicleDao;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IDriverService;
import com.sav.autobase.services.exception.ServiceException;

@Service
public class DriverService implements IDriverService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Inject
	private ITripDao tripDao;

	@Inject
	private IVehicleDao vehicleDao;

	@Override
	public Trip getTrip(Users user) throws ServiceException {
		if (user == null) {
			return null;
		}
		try {
			return tripDao.getByUser(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Vehicle getVehicle(Users user) throws ServiceException {
		if (user == null) {
			return null;
		}
		try {
			return vehicleDao.getByUser(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void changeStatusVehicle(Users user) throws ServiceException {
		if (user == null) {
			LOGGER.error("Failed change status vehicle");
			return;
		}
		try {
			Vehicle vehicle = vehicleDao.getByUser(user);
			vehicle.setReadyCrashCar(!vehicle.isReadyCrashCar());
			vehicleDao.update(vehicle);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Change's status vehicle");
	}

	@Override
	public void changeStatusTrip(Users user) throws ServiceException {
		if (user == null) {
			LOGGER.error("Failed change status trip");
			return;
		}
		try {
			Trip trip = tripDao.getByUser(user);
			trip.setEndTrip(!trip.isEndTrip());
			tripDao.update(trip);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Change's status trip");
	}

}

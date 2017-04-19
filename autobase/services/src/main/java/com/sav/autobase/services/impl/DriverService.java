package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.ITripDao;
import com.sav.autobase.dao.impl.db.IVehicleDao;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IDriverService;
import com.sav.autobase.services.exception.DAOException;

@Service
public class DriverService implements IDriverService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Inject
	private ITripDao tripDao;

	@Inject
	private IVehicleDao vehicleDao;

	@Override
	public Trip getTrip(Users user) throws DAOException {
		if (user != null) {
			try {
				return tripDao.getByUser(user);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}
	
	@Override
	public void changeStatusVehicle(Users user) throws DAOException {
		if (user != null) {
			try {
				Vehicle vehicle = vehicleDao.getByUser(user);
				vehicle.setReadyCrashCar(!vehicle.isReadyCrashCar());
				vehicleDao.update(vehicle);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Change's status vehicle");
		} else
			LOGGER.info("Failed change status vehicle");
	}

	@Override
	public void changeStatusTrip(Users user) throws DAOException {
		if (user != null) {
			try {
				Trip trip = getTrip(user);
				if (trip.getEndTrip() == false) {
					trip.setEndTrip(true);
					tripDao.update(trip);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Change's status trip");
		} else
			LOGGER.info("Failed change status trip");
	}


}

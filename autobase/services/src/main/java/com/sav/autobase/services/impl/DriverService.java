package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.ITripDao;
import com.sav.autobase.dao.impl.db.IVehicleDao;
import com.sav.autobase.datamodel.Trip;
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
	public Trip getTrip(Integer id) throws DAOException {
		if (id != null) {
			try {
				return tripDao.joinGetById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
		} else
			return null;
	}

	@Override
	public Trip changeStatusTrip(Trip trip) throws DAOException {
		if (trip != null) {
			try {
				trip.setEndTrip(true);
				return tripDao.update(trip);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Vehicle changeStatusVehicle(Vehicle vehicle) throws DAOException {
		if (vehicle != null) {
			try {
				vehicle.setReadyCrashCar(true);
				vehicleDao.update(vehicle);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
		}
		return null;
	}

}

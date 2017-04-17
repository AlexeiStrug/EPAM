package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IRequestDao;
import com.sav.autobase.dao.impl.db.ITripDao;
import com.sav.autobase.dao.impl.db.IVehicleDao;
import com.sav.autobase.dao.impl.db.filters.VehicleSerachCriteria;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IDispatcherService;
import com.sav.autobase.services.exception.DAOException;

@Service
public class DispatcherService implements IDispatcherService {

	private final static Logger LOGGER = LoggerFactory.getLogger(DispatcherService.class);

	@Inject
	private IRequestDao requestDao;

	@Inject
	private ITripDao tripDao;

	@Inject
	private IVehicleDao vehicleDao;

	@Override
	public Request getRequest() throws DAOException {
		try {
			Request request = findByStatus(StatusRequest.notReady);
			request.setProcessed(StatusRequest.inProcess);
			return requestDao.update(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Request findByStatus(StatusRequest status) throws DAOException {
		if (status != null) {
			try {
				return requestDao.joinFindByProcessed(status);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
		} else
			return null;
	}

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
	public List<Trip> getAllTrip() throws DAOException {
		try {
			return tripDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void modifyRequest(Request request) throws DAOException {
		if (request != null) {
			try {
				requestDao.update(request);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
			LOGGER.info(
					"Updated request Request.id={}. start_date={}. end_date={}. place_id={}. count_of_passenger={}. processed={} ",
					request.getId(), request.getStartDate(), request.getEndDate(), request.getPlace(),
					request.getCountOfPassenger(), request.getProcessed().name());
		} else
			LOGGER.info("Failed update request");
	}

	@Override
	public void deleteTrip(Integer id) throws DAOException {
		if (id != null) {
			try {
				tripDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
			LOGGER.info("Deleted request");
		} else
			LOGGER.info("Failed delete request");
	}

	@Override
	public void createTrip(Request request, Vehicle vehicle) throws DAOException {
		if (request != null && vehicle != null) {
			Trip newTrip = new Trip();
			try {
				request.setProcessed(StatusRequest.ready);
				requestDao.update(request);
				newTrip.setRequest(request);
				newTrip.setVehicle(vehicle);
				newTrip.setEndTrip(false);
				tripDao.insert(newTrip);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
			LOGGER.info("Create new trip Trip.id={}. request_id={}. vehicle_id={}. end_trip={} ", newTrip.getId(),
					newTrip.getRequest().getId(), newTrip.getVehicle().getId(), newTrip.getEndTrip());
		} else
			LOGGER.info("Failed created new trip");
	}

	@Override
	public void modifyTrip(Trip trip) throws DAOException {
		if (trip != null) {
			try {
				if (trip.getEndTrip() == null) {
					trip.setEndTrip(false);
					tripDao.update(trip);
				}
				tripDao.update(trip);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
			LOGGER.info("Updated trip Trip.id={}. request_id={}. vehicle_id={}. end_trip={} ", trip.getId(),
					trip.getRequest().getId(), trip.getVehicle().getId(), trip.getEndTrip());
		} else
			LOGGER.info("Failed update trip");
	}

	@Override
	public List<Vehicle> findByCriteria(VehicleSerachCriteria criteria) throws DAOException {
		if (criteria != null) {
			try {
				if (criteria.isReadyCrashCar() == true) {
					return vehicleDao.getFiltered(criteria);
				} else
					return null;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
		} else
			return vehicleDao.joinGetAllReadyCar(true);
	}

}

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
import com.sav.autobase.datamodel.Users;
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
	public Request getRequestByStatus(Users user) throws DAOException {
		try {
			Request request = findByStatus(StatusRequest.notReady);
			request.setProcessed(StatusRequest.inProcess);
			request.setDispatcher(user);
			return requestDao.update(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
	}

	@Override
	public Request getRequest(Integer id) throws DAOException {
		if (id != null) {
			try {
				return requestDao.joinGetById(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return null;
	}

	@Override
	public Request findByStatus(StatusRequest status) throws DAOException {
		if (status != null) {
			try {
				return requestDao.joinFindByProcessed(status);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
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
				throw new DAOException();
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
			throw new DAOException();
		}
	}

	@Override
	public void modifyRequest(Request request) throws DAOException {
		if (request != null) {
			try {
				requestDao.update(request);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
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
				throw new DAOException();
			}
			LOGGER.info("Deleted request");
		} else
			LOGGER.info("Failed delete request");
	}

	@Override
	public Trip createTrip(Request request, Vehicle vehicle) throws DAOException {
		if (request != null && vehicle != null) {
			Trip newTrip = new Trip();
			try {
				request.setProcessed(StatusRequest.ready);
				requestDao.update(request);
				newTrip.setRequest(request);
				newTrip.setVehicle(vehicle);
				newTrip.setEndTrip(false);
				Trip result = tripDao.insert(newTrip);
				LOGGER.info("Create new trip Trip.id={}. request_id={}. vehicle_id={}. end_trip={} ", newTrip.getId(),
						newTrip.getRequest().getId(), newTrip.getVehicle().getId(), newTrip.isEndTrip());
				return result;
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else {
			LOGGER.info("Failed created new trip");
			return null;
		}
	}

	@Override
	public void modifyTrip(Trip trip) throws DAOException {
		if (trip != null) {
			try {
				tripDao.update(trip);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
			LOGGER.info("Updated trip Trip.id={}. request_id={}. vehicle_id={}. end_trip={} ", trip.getId(),
					trip.getRequest().getId(), trip.getVehicle().getId(), trip.isEndTrip());
		} else
			LOGGER.info("Failed update trip");
	}

	@Override
	public List<Vehicle> findByCriteria(VehicleSerachCriteria criteria) throws DAOException {
		if (criteria != null) {
			try {
					return vehicleDao.getFiltered(criteria);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException();
			}
		} else
			return vehicleDao.joinGetAllReadyCar(true);
	}
}

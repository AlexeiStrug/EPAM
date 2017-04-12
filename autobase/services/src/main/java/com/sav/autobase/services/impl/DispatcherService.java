package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IRequestDao;
import com.sav.autobase.dao.impl.db.ITripDao;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.services.IDispatcherService;
import com.sav.autobase.services.exception.DAOException;

@Service
public class DispatcherService implements IDispatcherService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Inject
	private IRequestDao requestDao;

	@Inject
	private ITripDao tripDao;

	@Override
	public Request getRequest(Integer id) throws DAOException {
		try {
			return requestDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<Request> getAllRequest() throws DAOException {
		try {
			return requestDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Request findByProcessed(String processed) throws DAOException {
		if (processed != null) {
			try {
				return requestDao.joinFindByProcessed(processed);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
		} else
			return null;
	}

	@Override
	public Trip getTrip(Integer id) throws DAOException {
		try {
			return tripDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
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
			LOGGER.info("Updated request Request.id={}. start_date={}. end_date={}. place_id={}. count_of_passenger={}. processed={} ",
					request.getId(), request.getStartDate(), request.getEndDate(), request.getPlace(),
					request.getCountOfPassenger(), request.getProcessed().name());
		} else
			LOGGER.info("Failed update request");
	}

	@Override
	public void deleteRequest(Integer id) throws DAOException {
		if (id != null) {
			try {
				requestDao.delete(id);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
			LOGGER.info("Deleted request");
		} else
			LOGGER.info("Failed delete request");
	}

	@Override
	public void createTrip(Trip trip) throws DAOException {
		if (trip != null) {
			try {
				if (trip.getEndTrip() == null) {
					trip.setEndTrip(false);
				}
				tripDao.insert(trip);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e.getMessage());
			}
			LOGGER.info("Create new trip Trip.id={}. request_id={}. vehicle_id={}. end_trip={} ", trip.getId(),
					trip.getRequest().getId(), trip.getVehicle().getId(), trip.getEndTrip());
		} else
			LOGGER.info("Failed created new trip");
	}

	@Override
	public void modifyTrip(Trip trip) throws DAOException {
		if (trip != null) {
			try {
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

}

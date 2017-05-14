package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.api.IRequestDao;
import com.sav.autobase.dao.api.ITripDao;
import com.sav.autobase.dao.api.IVehicleDao;
import com.sav.autobase.dao.api.filter.VehicleSerachCriteria;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Trip;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.datamodel.Vehicle;
import com.sav.autobase.services.IDispatcherService;
import com.sav.autobase.services.exception.ServiceException;

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
	public Request getRequestByStatus(Users user) throws ServiceException {
		if (user == null) {
			return null;
		}
		try {
			Request request = findByStatus(StatusRequest.notReady);
			request.setProcessed(StatusRequest.inProcess);
			request.setDispatcher(user);
			return requestDao.update(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Request getRequest(Integer id) throws ServiceException {
		if (id == null) {
			return null;
		}
		try {
			return requestDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Request findByStatus(StatusRequest status) throws ServiceException {
		if (status == null) {
			return null;
		}
		try {
			return requestDao.joinFindByProcessed(status);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public Trip getTrip(Integer id) throws ServiceException {
		if (id != null) {
			return null;
		}
		try {
			return tripDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public List<Trip> getAllTrip() throws ServiceException {
		try {
			return tripDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void modifyRequest(Request request) throws ServiceException {
		if (request == null) {
			LOGGER.error("Failed update request");
			return;
		}
		try {
			requestDao.update(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info(
				"Updated request Request.id={}. start_date={}. end_date={}. place_id={}. count_of_passenger={}. comment={}. processed={} ",
				request.getId(), request.getStartDate(), request.getEndDate(), request.getPlace(),
				request.getCountOfPassenger(), request.getComment(), request.getProcessed().name());
	}

	@Override
	public void deleteTrip(Integer id) throws ServiceException {
		if (id == null) {
			LOGGER.error("Failed delete request");
			return;
		}
		try {
			tripDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Deleted request");
	}

	@Override
	public Trip createTrip(Request request, Vehicle vehicle) throws ServiceException {
		if (request == null && vehicle == null) {
			LOGGER.error("Failed created new trip");
			return null;
		}
		Trip newTrip = new Trip();
		try {
			request.setProcessed(StatusRequest.ready);
			requestDao.update(request);
			newTrip.setRequest(request);
			newTrip.setVehicle(vehicle);
			newTrip.setEndTrip(false);
			Trip result = tripDao.insert(newTrip);
			LOGGER.info("Create new trip Trip.id={}. request_id={}. vehicle_id={}. end_trip={} ", newTrip.getId(),
					newTrip.getRequest(), newTrip.getVehicle(), newTrip.isEndTrip());
			return result;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void modifyTrip(Trip trip) throws ServiceException {
		if (trip == null) {
			LOGGER.error("Failed update trip");
			return;
		}
		try {
			tripDao.update(trip);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Updated trip Trip.id={}. request_id={}. vehicle_id={}. end_trip={} ", trip.getId(),
				trip.getRequest(), trip.getVehicle(), trip.isEndTrip());
	}

	@Override
	public List<Vehicle> findVehicleByCriteria(VehicleSerachCriteria criteria) throws ServiceException {
		if (criteria == null) {
			return vehicleDao.joinGetAllReadyCar(true);
		}
		try {
			return vehicleDao.getFiltered(criteria);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

}

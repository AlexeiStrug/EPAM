package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sav.autobase.dao.impl.db.IRequestDao;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.services.IClientService;
import com.sav.autobase.services.exception.DAOException;
import com.sav.autobase.services.exception.ModifyException;

public class ClientService implements IClientService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Inject
	private IRequestDao requestDao;

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

	@SuppressWarnings("unused")
	@Override
	public void createRequest(Request request) throws DAOException {
		try {
			if (request.getProcessed() == null) {
				request.setProcessed(StatusRequest.notReady);
			}
			requestDao.insert(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
		if (request != null)
			LOGGER.info("Created new request Request.id={}. start_date={}. end_date={}. place_id={}. count_of_passenger={} ",
					request.getId(), request.getStartDate(), request.getEndDate(), request.getPlace(),
					request.getCountOfPassenger());
		else
			LOGGER.info("Failed created new request");
	}

	@SuppressWarnings("unused")
	@Override
	public void modifyRequest(Request request) throws DAOException, ModifyException {
		try {
			if (request.getProcessed() == StatusRequest.ready) {
				throw new ModifyException();
			}
			requestDao.update(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
		if (request != null)
			LOGGER.info("Updated requestRequest.id={}. start_date={}. end_date={}. place_id={}. count_of_passenger={} ",
					request.getId(), request.getStartDate(), request.getEndDate(), request.getPlace(),
					request.getCountOfPassenger());
		else
			LOGGER.info("Failed update request");
	}

	@Override
	public void deleteRequest(Integer id) throws DAOException {
		try {
			requestDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
		if (id != null)
			LOGGER.info("Deleted request");
		else
			LOGGER.info("Failed delete request");
	}

	@SuppressWarnings("unused")
	@Override
	public void saveAllRequest(List<Request> requests) throws DAOException {
		try {
			for (Request request : requests) {
				createRequest(request);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
		if (requests != null)
			LOGGER.info("Saved all requests");
		else
			LOGGER.info("Failed save all request");

	}
}

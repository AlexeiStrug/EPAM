package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sav.autobase.dao.impl.db.IRequestDao;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.services.IClientService;
import com.sav.autobase.services.exception.DAOexception;

public class ClientService implements IClientService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Inject
	private IRequestDao requestDao;

	@Override
	public Request getRequest(Integer id) throws DAOexception {
		try {
			return requestDao.joinGetById(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOexception(e.getMessage());
		}
	}

	@Override
	public List<Request> getAllRequest() throws DAOexception {
		try {
			return requestDao.joinGetAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOexception(e.getMessage());
		}
	}

	@Override
	public void createRequest(Request request) throws DAOexception {
		try {
			requestDao.insert(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOexception(e.getMessage());
		}
		if (request != null)
			LOGGER.info("Created new request");
		else
			LOGGER.info("Failed created new request");
	}

	@Override
	public void modifyRequest(Request request) throws DAOexception {
		try {
			requestDao.update(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOexception(e.getMessage());
		}
		if (request != null)
			LOGGER.info("Updated request");
		else
			LOGGER.info("Failed update request");
	}

	@Override
	public void deleteRequest(Integer id) throws DAOexception {
		try {
			requestDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOexception(e.getMessage());
		}
		if (id != null)
			LOGGER.info("Deleted request");
		else
			LOGGER.info("Failed delete request");
	}

	@Override
	public void saveAllRequest(List<Request> requests) throws DAOexception {
		try {
			for (Request request : requests) {
				createRequest(request);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOexception(e.getMessage());
		}
		if (requests != null)
			LOGGER.info("Saved all requests");
		else
			LOGGER.info("Failed save all request");

	}
}

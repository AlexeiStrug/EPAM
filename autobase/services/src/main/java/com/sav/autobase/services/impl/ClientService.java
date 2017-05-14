package com.sav.autobase.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.api.IRequestDao;
import com.sav.autobase.datamodel.Request;
import com.sav.autobase.datamodel.StatusRequest;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IClientService;
import com.sav.autobase.services.exception.ServiceException;
import com.sav.autobase.services.exception.ModifyException;

@Service
public class ClientService implements IClientService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	@Inject
	private IRequestDao requestDao;

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
	public List<Request> getAllRequest(Users user) throws ServiceException {
		if (user == null) {
			LOGGER.error("Failed getAll request by user");
			return null;
		}
		try {
			return requestDao.joinGetAllbyUser(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
	}

	@Override
	public void createRequest(Request request) throws ServiceException, ModifyException {
		if (request == null) {
			LOGGER.error("Failed created new request");
			return;
		}
		try {
			if (request.getId() == null) {
				if (request.getProcessed() == null) {
					request.setProcessed(StatusRequest.notReady);
				}
				requestDao.insert(request);
			} else {
				modifyRequest(request);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info(
				"Created new request Request.id={}. client={}. start_date={}. end_date={}. place={}. count_of_passenger={}. comment={}. processed={} ",
				request.getId(), request.getClient(), request.getStartDate(), request.getEndDate(), request.getPlace(),
				request.getCountOfPassenger(), request.getComment(), request.getProcessed().name());
	}

	@Override
	public void modifyRequest(Request request) throws ServiceException, ModifyException {
		if (request == null) {
			LOGGER.error("Failed update request");
			return;
		}
		try {
			if (request.getProcessed() == null) {
				request.setProcessed(StatusRequest.notReady);
				requestDao.update(request);
			}
			if (request.getProcessed() == StatusRequest.ready) {
				throw new ModifyException();
			}
			if (request.getDispatcher() == null) {
				requestDao.updateClientRequest(request);
			} else
				requestDao.update(request);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info(
				"Updated request Request.id={}. start_date={}. end_date={}. place={}. count_of_passenger={}. comment={}. processed={} ",
				request.getId(), request.getStartDate(), request.getEndDate(), request.getPlace(),
				request.getCountOfPassenger(), request.getComment(), request.getProcessed().name());
	}

	@Override
	public void deleteRequest(Integer id) throws ServiceException {
		if (id == null && requestDao.getById(id).getProcessed() == StatusRequest.inProcess) {
			LOGGER.error("Failed delete request");
			return;
		}
		try {
			requestDao.delete(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Deleted request");
	}
}

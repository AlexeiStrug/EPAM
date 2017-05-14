package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.api.IUsersDao;
import com.sav.autobase.datamodel.TypeUsers;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IAuthenticationService;
import com.sav.autobase.services.exception.ModifyException;
import com.sav.autobase.services.exception.ServiceException;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Inject
	private IUsersDao usersDao;

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Override
	public Users authenticate(String UserLogin, String UserPassword) throws ServiceException {

		if (UserLogin == null || UserLogin == null) {
			LOGGER.error("Failed enter data for authenticate");
			return null;
		}
		Users authentication = null;
		try {
			authentication = usersDao.findByloginPassword(UserLogin, UserPassword);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		if (authentication != null) {
			LOGGER.info("Success authenticate User = " + UserLogin);
		} else {
			LOGGER.error("Failed authenticate");
		}
		return authentication;
	}

	@Override
	public void register(Users newUsers) throws ServiceException, ModifyException {
		if (newUsers == null) {
			LOGGER.error("Failed register new User");
			return;
		}
		try {
			if (newUsers.getType().name() == null) {
				newUsers.setType(TypeUsers.client);
				usersDao.insert(newUsers);
			}
			if (newUsers.getType() == TypeUsers.administator) {
				throw new ModifyException();
			} else
				usersDao.insert(newUsers);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException();
		}
		LOGGER.info("Register new User");
	}

}

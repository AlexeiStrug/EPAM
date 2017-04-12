package com.sav.autobase.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sav.autobase.dao.impl.db.IUsersDao;
import com.sav.autobase.datamodel.Users;
import com.sav.autobase.services.IAuthenticationService;
import com.sav.autobase.services.exception.DAOException;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Inject
	private IUsersDao usersDao;

	@Override
	public Boolean authenticate(String UserLogin, String UserPassword) throws DAOException {
		Users authentication = null;
		try {
			authentication = usersDao.findByloginPassword(UserLogin, UserPassword);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
		if (authentication != null)
			LOGGER.info("Success authenticate User = " + UserLogin);
		else
			LOGGER.info("Failed authenticate");

		return authentication != null;
	}

	@Override
	public void register(Users newUsers) throws DAOException {
		try {
			usersDao.insert(newUsers);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
		if (newUsers != null)
			LOGGER.info("Register new User");
		else
			LOGGER.info("Failed register new User");
	}

}
